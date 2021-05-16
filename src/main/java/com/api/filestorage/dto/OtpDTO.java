package com.api.filestorage.dto;

import java.time.LocalDateTime;

import com.api.filestorage.entities.OtpEntity;

public class OtpDTO {

    private String uuid;

    private int code;

    private String email;
    private LocalDateTime expire_time;

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getExpire_time() {
        return this.expire_time;
    }

    public void setExpire_time(LocalDateTime expire_time) {
        this.expire_time = expire_time;
    }

    public OtpDTO toDTO(OtpEntity entity) {
        this.uuid = entity.getUuid();
        this.code = entity.getCode();
        this.email = entity.getEmail();
        this.expire_time = entity.getExpireTime();
        return this;
    }

}
