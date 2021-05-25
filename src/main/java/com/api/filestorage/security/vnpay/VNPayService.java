package com.api.filestorage.security.vnpay;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.api.filestorage.entities.AccountPackageEntity;
import com.api.filestorage.entities.BillHistoryEntity;
import com.api.filestorage.entities.UserEntity;
import com.api.filestorage.repository.AccountPackageRepository;
import com.api.filestorage.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VNPayService {
    @Autowired
    private AccountPackageRepository accountPackageRepository;

    @Autowired
    private UserRepository userRepository;

    public String generateStringURL(Map<String, Object> data) {
        String username = data.get("username").toString();
        int id = Integer.parseInt(data.get("pkg_id").toString());
        AccountPackageEntity entity = accountPackageRepository.findById(id).get();
        String vnp_TxnRef = String.valueOf((int) (Math.random() * Math.pow(10, 5)));
        HashMap<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", Config.vnp_Version);
        vnp_Params.put("vnp_Command", Config.vnp_Command);
        vnp_Params.put("vnp_TmnCode", Config.vnp_TmnCode);
        vnp_Params.put("vnp_Amount", entity.getPrice() * 100 + "");
        vnp_Params.put("vnp_CurrCode", Config.vnp_CurrCode);
        vnp_Params.put("vnp_BankCode", Config.vnp_BankCode);
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", Config.vnp_OrderInfo);
        vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl + "?username=" + username);
        vnp_Params.put("vnp_Locale", Config.vnp_Locale);
        vnp_Params.put("vnp_Merchant", Config.vnp_Merchant);
        vnp_Params.put("vnp_IpAddr", Config.vnp_IpAddr);

        vnp_Params.put("vnp_CreateDate", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));

        String vnp_SecureHash = Config.vnp_hashSecret + Config.concatAllFields(vnp_Params);
        // encoding sha-256
        String encodedString = Config.encodeSHA256(vnp_SecureHash);
        String queryUrl = Config.concatAllFields(vnp_Params) + "&vnp_SecureHashType=SHA256&vnp_SecureHash="
                + encodedString;

        return Config.vnp_PayUrl + "?" + queryUrl;
    }

    public void processSuccess(Map<String, String> data) {
        // username, amount
        String username = data.get("username");
        int price = Integer.parseInt(data.get("amount"));

        AccountPackageEntity accountPackageEntity = accountPackageRepository.findByPrice(price);

        UserEntity userEntity = userRepository.findByUsername(username);
        userEntity.setAcc_pkg(accountPackageEntity);

        BillHistoryEntity newBillHistory = new BillHistoryEntity();
        newBillHistory.setCreate_date(LocalDate.now());
        newBillHistory.setExpire_date(LocalDate.now().plusMonths(1));
        newBillHistory.setUser(userEntity);
        newBillHistory.setAmount(price);
        userEntity.getBills().add(newBillHistory);
        userRepository.save(userEntity);

    }

}
