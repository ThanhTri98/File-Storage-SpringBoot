package com.api.filestorage.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.api.filestorage.dto.auth.DataRequestOTP;
import com.api.filestorage.entities.FilesEntity;
import com.api.filestorage.entities.UserEntity;
import com.api.filestorage.security.payload.request.LoginRequest;
import com.api.filestorage.services.UserService;
import com.api.filestorage.services.ClazzData.DataShared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserEntity> findAll() {
        return userService.findAll();
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok().body(userService.signIn(loginRequest));
    }

    @PostMapping("/signup/validate/otp")
    public ResponseEntity<?> validateOtp(@RequestBody DataRequestOTP dataRequestOTP) {
        if (userService.validateOtpSignup(dataRequestOTP.getUuid(), dataRequestOTP.getCode())) {
            // Dang ky
            userService.signUp(dataRequestOTP.getInfor());
            return ResponseEntity.ok().body("OK");
        } else
            return ResponseEntity.badRequest().body("FAIL");

    }

    @PostMapping("/signup/validate/user")
    public ResponseEntity<?> validateUser(@RequestBody Map<String, String> dataReq) {
        // username, email, uuid
        String username = dataReq.get("username");
        String email = dataReq.get("email");
        String uuid = dataReq.get("uuid");
        boolean userValid = userService.findByUsername(username) != null;
        boolean emailValid = userService.findByEmail(email) != null;
        if (userValid && emailValid)
            return new ResponseEntity<>(new String[] { "Tên tài khoản đã tồn tại!", "Email đã tồn tại!" },
                    HttpStatus.OK);
        if (userValid)
            // return ResponseEntity.badRequest().body("Tên tài khoản đã tồn tại!");
            return new ResponseEntity<>(new String[] { "Tên tài khoản đã tồn tại!" }, HttpStatus.OK);
        if (emailValid)
            // return ResponseEntity.badRequest().body("Email đã tồn tại!");
            return new ResponseEntity<>(new String[] { "Email đã tồn tại!" }, HttpStatus.OK);
        // send mail
        new Thread(new Runnable() {
            @Override
            public void run() {
                userService.sendEmail(email, uuid);
            }

        }).start();
        return ResponseEntity.ok().body("");
    }

    @GetMapping("/user/{userName}")
    public long getUsedMemory(@PathVariable String userName) {
        return userService.getUsedMemory(userName);
    }

    @GetMapping("/user/total/{price}")
    public long getTotalMemory(@PathVariable int price) {
        return userService.getTotalMemory(price);
    }

    // 20210523
    @GetMapping("/user/trash/{username}")
    public List<FilesEntity> getTrash(@PathVariable String username) {
        return userService.getTrash(username);
    }

    @GetMapping("/user/shared/all/{username}")
    public List<DataShared> getShared(@PathVariable String username) {
        return userService.getShared(username);
    }

    @GetMapping("/user/shared/{text}")
    public String findByUserNameOrEmail(@PathVariable String text) {
        return userService.findByUserNameOrEmail(text);
    }

    @GetMapping(value = { "/user/search/{creator}/{query}", "/user/search/{creator}" })
    public List<FilesEntity> getListSearch(@PathVariable String creator, @PathVariable String query) {
        if (query == null)
            return new ArrayList<>();
        return userService.getListSearch(creator, query);
    }
}
