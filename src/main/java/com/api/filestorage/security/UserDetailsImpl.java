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
    // private int is_active;
    private Collection<? extends GrantedAuthority> authorities;
    private String acc_pkg_name;
    private long acc_pkg_size;

    public UserDetailsImpl(String username, String password, String email, String full_name, String acc_pkg_name,
            long acc_pkg_size, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.full_name = full_name;
        this.authorities = authorities;
        this.acc_pkg_name = acc_pkg_name;
        this.acc_pkg_size = acc_pkg_size;
    }

    public static UserDetailsImpl build(UserDTO user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

        return new UserDetailsImpl(user.getUsername(), user.getPassword(), user.getEmail(), user.getFull_name(),
                user.getAcc_pkg_name(), user.getAcc_pkg_size(), authorities);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAuthorities(Collection<?extends GrantedAuthority> authorities) {
        this.authorities = authorities;
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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UserDetailsImpl)) {
            return false;
        }
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) o;
        return Objects.equals(username, userDetailsImpl.username) && Objects.equals(password, userDetailsImpl.password) && Objects.equals(full_name, userDetailsImpl.full_name) && Objects.equals(email, userDetailsImpl.email) && Objects.equals(authorities, userDetailsImpl.authorities) && Objects.equals(acc_pkg_name, userDetailsImpl.acc_pkg_name) && acc_pkg_size == userDetailsImpl.acc_pkg_size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, full_name, email, authorities, acc_pkg_name, acc_pkg_size);
    }

}
