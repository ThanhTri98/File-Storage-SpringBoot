package com.api.filestorage.security.payload.response;

import java.util.Set;

public class JwtResponse {
    private String token;
    private final String type = "Bearer";
    private String username;
    private String email;
    private String full_name;
    private int is_active;
    private Set<String> roles;

    public JwtResponse(String accessToken, String username, String email, String full_name, int is_active,
            Set<String> roles) {
        this.token = accessToken;
        this.username = username;
        this.email = email;
        this.full_name = full_name;
        this.is_active = is_active;
        this.roles = roles;
    }

    public int getIs_active() {
        return this.is_active;
    }

    public String getToken() {
        return this.token;
    }

    public String getType() {
        return this.type;
    }

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
