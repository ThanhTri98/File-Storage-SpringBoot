package com.api.filestorage.dto;

import com.api.filestorage.entities.RoleEntity;

public class RoleDTO {
    private int id;
    private String name;

    public void setId(int id) {
        this.id = id;
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

    public RoleDTO() {
    }

    public RoleDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoleDTO toDTO(RoleEntity role) {
        this.id = role.getId();
        this.name = role.getName();
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }


}
