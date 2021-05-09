package com.api.filestorage.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.api.filestorage.entities.UserEntity;

//Data transfer object
public class UserDTO {
    private String username;
    private String password;
    private String full_name;
    private String email;
    private int is_active;
    private Set<RoleDTO> roles = new HashSet<>();

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFull_name() {
        return this.full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIs_active() {
        return this.is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    public Set<RoleDTO> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }

    public UserDTO toDTO(UserEntity user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.full_name = user.getFull_name();
        this.email = user.getEmail();
        this.is_active = user.getIs_active();
        this.roles = user.getRoles().stream().map(entity -> {
            return new RoleDTO().toDTO(entity);
        }).collect(Collectors.toSet());
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", full_name='" + getFull_name() + "'" +
            ", email='" + getEmail() + "'" +
            ", is_active='" + getIs_active() + "'" +
            ", roles='" + getRoles().toString() + "'" +
            "}";
    }

}
