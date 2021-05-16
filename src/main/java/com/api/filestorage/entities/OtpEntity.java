
package com.api.filestorage.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.api.filestorage.dto.OtpDTO;

@Entity
@Table(name = "otp_code")
public class OtpEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String uuid;

    private int code;

    private String email;
    @Column(name = "expire_time")
    private LocalDateTime expireTime;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

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


    public LocalDateTime getExpireTime() {
        return this.expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public OtpEntity toEntity(OtpDTO dto) {
        this.uuid = dto.getUuid();
        this.code = dto.getCode();
        this.email = dto.getEmail();
        this.expireTime = dto.getExpire_time();
        return this;
    }

}
