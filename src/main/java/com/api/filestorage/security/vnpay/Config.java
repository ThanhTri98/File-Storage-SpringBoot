package com.api.filestorage.security.vnpay;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Config {
    static final String vnp_Version = "2.0.0";
    static final String vnp_Command = "pay";
    static final String vnp_OrderInfo = "NapTien";
    static final String vnp_ReturnUrl = "http://localhost:3000/returnvnpay";
    static final String vnp_PayUrl = "http://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
    static final String vnp_CurrCode = "VND";
    static final String vnp_BankCode = "NCB";
    static final String vnp_Locale = "vn";
    static final String vnp_Merchant = "FileStorage";

    static final String vnp_IpAddr = "171.248.171.115";
    static final String vnp_TmnCode = "D9MJUFM5";
    static final String vnp_hashSecret = "UKHJNECSLUBBETVKEMVABTASUAVDYFDW";

    static String encodeSHA256(String vnp_SecureHash) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(vnp_SecureHash.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(encodedHash);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    static String concatAllFields(HashMap<String, String> vnp_Params) {
        // create a list and sort it
        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);
        // create a buffer for the md5 input and add the secure secret first
        StringBuilder sb = new StringBuilder();
        Iterator<String> itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                sb.append(fieldName);
                sb.append("=");
                try {
                    sb.append(URLDecoder.decode(fieldValue, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            if (itr.hasNext()) {
                sb.append("&");
            }
        }
        return sb.toString();
    }

    static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}