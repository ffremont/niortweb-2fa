package com.github.ffremont.mfa.resource;

import com.github.ffremont.mfa.ResourceException;
import com.github.ffremont.mfa.image.QrCodeUtils;
import com.github.ffremont.mfa.model.Signin;
import com.github.ffremont.mfa.model.User;
import com.github.ffremont.mfa.security.TimeBasedOneTimePassword;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.coyote.Response;
import org.springframework.boot.web.server.Cookie;
import org.springframework.boot.web.servlet.server.CookieSameSiteSupplier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.websocket.server.PathParam;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpCookie;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserResource {

    /**
     * Collection des utilisateurs
     */
    static final Map<String, User> USERS = new ConcurrentHashMap<>() {
        {
            this.put("ffremont", new User("ffremont", DigestUtils.sha256Hex("azerty"), "Florent", "MVQTMZJSMI3WILLFMI4TSLJUMIYDKLLC"));
        }
    };
    static final String JWT_SECRET = "8eb3d95f-b68d-4366-b5b5-014174ec0477";

    private final TimeBasedOneTimePassword totp = new TimeBasedOneTimePassword();

    @PostMapping(value = "{login}/signin", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<User> login(@PathVariable String login, Signin signin) throws NoSuchAlgorithmException, InvalidKeyException {
        User user = Optional.ofNullable(USERS.get(login)).orElseThrow(() -> new ResourceException("utilisateur introuvable", 401));
        if (!user.getHashPass().equals(DigestUtils.sha256Hex(signin.getPassword()))) {
            throw new ResourceException("password invalide", 401);
        }

        if (!totp.isTokenValid(user.getSecretTotp(), signin.getCode())) {
            throw new ResourceException("TOTP invalide", 401);
        }
        log.info("🥳 connexion avec succès pour {} !", user.getLogin());

        Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
        final String jws = Jwts.builder().setSubject(user.getLogin()).signWith(key).compact();
        HttpCookie cookie = new HttpCookie("my-auth-cookie", jws);
        cookie.setHttpOnly(true);

        return ResponseEntity.noContent().header("Set-Cookie", cookie.toString()).build();
    }

    @GetMapping(value = "{login}/2fa")
    public ResponseEntity<byte[]> totpExport(@PathVariable String login) throws IOException {
        final User user = USERS.get(login);

        // Create a byte array output stream.
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        // Write to output stream
        String txt = "otpauth://totp/{issuer}:{account}?secret={secret}&issuer={issuer}&algorithm=SHA256&digits=6&period=30"
                .replace("{issuer}", URLEncoder.encode("NiortWeb 2FA", StandardCharsets.UTF_8))
                .replace("{account}", user.getLogin())
                .replace("{secret}", user.getSecretTotp());
        log.info(txt);
        ImageIO.write(QrCodeUtils.gen(txt
        ), "jpg", bao);

        return ResponseEntity.ok().header("Cache-Control", "no-cache,no-store").contentType(MediaType.IMAGE_JPEG).body(bao.toByteArray());
    }

}
