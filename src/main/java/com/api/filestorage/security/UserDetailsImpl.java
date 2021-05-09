package com.api.filestorage.security;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.api.filestorage.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {
    private String username;
    @JsonIgnore
    private String password;
    private String full_name;
    private String email;
    private int is_active;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(String username, String password, String email, String full_name, int is_active,
            Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.is_active = is_active;
        this.full_name = full_name;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(UserDTO user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

        return new UserDetailsImpl(user.getUsername(), user.getPassword(), user.getEmail(),user.getFull_name(), user.getIs_active(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getFull_name() {
        return this.full_name;
    }

    public String getEmail() {
        return this.email;
    }

    public int getIs_active() {
        return this.is_active;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UserDetailsImpl)) {
            return false;
        }
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) o;
        return Objects.equals(username, userDetailsImpl.username) && Objects.equals(password, userDetailsImpl.password)
                && Objects.equals(full_name, userDetailsImpl.full_name) && Objects.equals(email, userDetailsImpl.email)
                && is_active == userDetailsImpl.is_active && Objects.equals(authorities, userDetailsImpl.authorities);
    }

}
