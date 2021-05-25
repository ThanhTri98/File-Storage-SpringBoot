package com.api.filestorage.security.payload.response;

import java.util.Set;

public class JwtResponse {
    private String token;
    // private final String type = "Bearer";
    private String username;
    private String email;
    private String full_name;
    // private int is_active;
    private Set<String> roles;

    private String acc_pkg_name;
    private long acc_pkg_size;

    public JwtResponse(String accessToken, String username, String email, String full_name, String acc_pkg_name,
            long acc_pkg_size, Set<String> roles) {
        this.token = accessToken;
        this.username = username;
        this.email = email;
        this.full_name = full_name;
        // this.is_active = is_active;
        this.roles = roles;
        this.acc_pkg_name = acc_pkg_name;
        this.acc_pkg_size = acc_pkg_size;
    }

    // public int getIs_active() {
    // return this.is_active;
    // }
    public void setToken(String token) {
        this.token = token;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getAcc_pkg_name() {
        return this.acc_pkg_name;
    }

    public void setAcc_pkg_name(String acc_pkg_name) {
        this.acc_pkg_name = acc_pkg_name;
    }

    public long getAcc_pkg_size() {
        return this.acc_pkg_size;
    }

    public void setAcc_pkg_size(long acc_pkg_size) {
        this.acc_pkg_size = acc_pkg_size;
    }

    public String getToken() {
        return this.token;
    }

    // public String getType() {
    // return this.type;
    // }

    public String getFull_name() {
        return this.full_name;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public Set<String> getRoles() {
        return roles;
    }
}
