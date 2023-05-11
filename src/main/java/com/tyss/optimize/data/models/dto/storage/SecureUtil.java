package com.tyss.optimize.data.models.dto.storage;

import com.mongodb.client.MongoClient;
import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.data.models.db.service.MongoUtilService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.spec.KeySpec;
import java.util.Base64;

@Component("secureUtilOld")
@Slf4j
public class SecureUtil {

    @Autowired
    private Environment env;

    @Autowired
    private MongoUtilService mongoUtilService;

    @Autowired
    @Qualifier(value = "mongoClient")
    MongoClient mongoClient;

    private final String ALGORITHM = "AES/CBC/PKCS5PADDING";
    private final byte[] keyValue = new byte[]{'T', 'h', 'i', 's', 'I', 's', 'A', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y'};
    private final IvParameterSpec ivSpec = new IvParameterSpec(keyValue);


    public String encrypt(String eValue) throws Exception {
        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
        return Base64.getEncoder()
                .encodeToString(cipher.doFinal(eValue.getBytes(StandardCharsets.UTF_8)));
    }

    public String decrypt(String deValue) throws Exception {
        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
        return new String(cipher.doFinal(Base64.getDecoder().decode(deValue)));
    }

    private Key generateKey() throws Exception {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(CommonConstants.SECRET_KEY.toCharArray(), CommonConstants.SALT.getBytes(), 655, 256);
        SecretKey tmp = factory.generateSecret(spec);
        return new SecretKeySpec(tmp.getEncoded(), "AES");
    }

}
