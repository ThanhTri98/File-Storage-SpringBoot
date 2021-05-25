package com.api.filestorage.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.api.filestorage.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    private String username;
    private String password;
    private String full_name;
    private String email;
    private int is_active;
    @ManyToOne
    @JoinColumn(name = "acc_pkg", nullable = false)
    private AccountPackageEntity acc_pkg;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<BillHistoryEntity> bills;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "videofile_shared", joinColumns = @JoinColumn(name = "owner"), inverseJoinColumns = @JoinColumn(name = "file_id"))
    private List<VideoFileEntity> videos_owner;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "videofile_shared", joinColumns = @JoinColumn(name = "receiver"), inverseJoinColumns = @JoinColumn(name = "file_id"))
    private List<VideoFileEntity> videos_receiver;

    public List<BillHistoryEntity> getBills() {
        return this.bills;
    }

    public void setBills(List<BillHistoryEntity> bills) {
        this.bills = bills;
    }

    public Set<RoleEntity> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    public AccountPackageEntity getAcc_pkg() {
        return this.acc_pkg;
    }

    public void setAcc_pkg(AccountPackageEntity acc_pkg) {
        this.acc_pkg = acc_pkg;
    }

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

    public UserEntity toEntity(UserDTO user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.full_name = user.getFull_name();
        this.email = user.getEmail();
        this.is_active = user.getIs_active();
        this.roles = user.getRoles().stream().map(r -> {
            return new RoleEntity().toEntity(r);
        }).collect(Collectors.toSet());
        return this;
    }

    public List<VideoFileEntity> getVideos_owner() {
        return this.videos_owner;
    }

    public void setVideos_owner(List<VideoFileEntity> videos_owner) {
        this.videos_owner = videos_owner;
    }

    public List<VideoFileEntity> getVideos_receiver() {
        return this.videos_receiver;
    }

    public void setVideos_receiver(List<VideoFileEntity> videos_receiver) {
        this.videos_receiver = videos_receiver;
    }

    @Override
    public String toString() {
        return "{" + " username='" + getUsername() + "'" + ", password='" + getPassword() + "'" + ", full_name='"
                + getFull_name() + "'" + ", email='" + getEmail() + "'" + ", is_active='" + getIs_active() + "'"
                + ", acc_pkg='" + getAcc_pkg() + "'" + ", roles='" + getRoles() + "'" + "}";
    }

}
