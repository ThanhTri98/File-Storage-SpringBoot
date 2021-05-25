package com.api.filestorage.security.vnpay;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VNPayController {
    @Autowired
    private VNPayService vnPayService;

    @PostMapping("/api/user/vnpay")
    public String process(@RequestBody Map<String, Object> data) {
        return vnPayService.generateStringURL(data);
    }

    @PostMapping("api/user/vnpay/success")
    public void processSuccess(@RequestBody Map<String, String> data){
        vnPayService.processSuccess(data);
    }

}
