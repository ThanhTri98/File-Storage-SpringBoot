package com.api.filestorage.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.api.filestorage.dto.RoleDTO;

@Entity
@Table(name = "role")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<UserEntity> users = new HashSet<>();;

    public Set<UserEntity> getUsers() {
        return this.users;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoleEntity() {
    }

    public RoleEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoleEntity toEntity(RoleDTO role) {
        this.id = role.getId();
        this.name = role.getName();
        return this;
    }
}
