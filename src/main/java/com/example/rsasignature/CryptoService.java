package com.example.rsasignature;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

@Service
public class CryptoService {

    @Autowired
    private KeyPairRepository keyPairRepository;

    private PublicKey publicKey;
    private PrivateKey privateKey;

    @PostConstruct
    public void initKeys() throws Exception {
        // Generate keys on startup and store them in the database
        KeyPair keyPair = RSASecurityUtil.generateKeyPair();
        KeyPairEntity entity = new KeyPairEntity();
        entity.setPublicKey(Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));
        entity.setPrivateKey(Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded()));
        keyPairRepository.save(entity);

        this.publicKey = keyPair.getPublic();
        this.privateKey = keyPair.getPrivate();
    }

    public String sign(String message) throws Exception {
        return RSASecurityUtil.sign(message, privateKey);
    }

    public boolean verify(String message, String signature) throws Exception {
        return RSASecurityUtil.verify(message, signature, publicKey);
    }
}