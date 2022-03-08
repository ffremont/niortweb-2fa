package com.github.ffremont.mfa.resource;


import com.github.ffremont.mfa.image.QrCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.lang3.StringUtils;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.UUID;

@RestController
@RequestMapping("/api/totp")
@Slf4j
public class TotpResource {

    public static String generateSecretKey() {
        SecureRandom random = new SecureRandom();
        // 20 octets -> 160 bits
        // il faut 5 bits pour encoder un caractère en base32
        // 160 / 5 => 32 caractères
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        Base32 base32 = new Base32();
        return base32.encodeToString(bytes);
    }

    @GetMapping("generate")
    public ResponseEntity<String> generateTotp(){

        return ResponseEntity.ok(generateSecretKey());
    }


}
