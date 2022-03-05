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
import java.util.UUID;

@RestController
@RequestMapping("/api/totp")
@Slf4j
public class TotpResource {

    @GetMapping("generate")
    public ResponseEntity<String> generateTotp(){
        return ResponseEntity.ok(StringUtils.truncate(new String(new Base32().encode(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8), 32));
    }


}
