package com.api.filestorage.dto.auth;

import com.api.filestorage.dto.UserDTO;

public class DataRequestOTP {
    private String uuid;
    private String code;
    private UserDTO infor;

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public UserDTO getInfor() {
        return this.infor;
    }

    public void setInfor(UserDTO infor) {
        this.infor = infor;
    }

}
