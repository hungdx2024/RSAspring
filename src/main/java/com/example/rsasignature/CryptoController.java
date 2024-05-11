package com.example.rsasignature;

import com.example.rsasignature.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/crypto")
public class CryptoController {

    @Autowired
    private CryptoService cryptoService;

    @GetMapping("/sign")
    public String sign(@RequestParam String message) throws Exception {
        return cryptoService.sign(message);
    }

    @PostMapping("/verify")
    public boolean verify(@RequestBody MessageDto req) throws Exception {
        return cryptoService.verify(req.getMessage(), req.getSignature());
    }
}








