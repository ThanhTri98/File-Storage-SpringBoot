package com.api.filestorage.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "accountpackage")
public class AccountPackageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private long total_size;
    private int price;

    @OneToMany(mappedBy = "acc_pkg")
    private Set<UserEntity> users;

    public Set<UserEntity> getUsers() {
        return this.users;
    }

    public AccountPackageEntity() {
    }

    public AccountPackageEntity(int id, String name, long total_size, int price) {
        this.id = id;
        this.name = name;
        this.total_size = total_size;
        this.price = price;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTotal_size() {
        return this.total_size;
    }

    public void setTotal_size(long total_size) {
        this.total_size = total_size;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", name='" + getName() + "'" + ", total_size='" + getTotal_size() + "'"
                + ", price='" + getPrice() + "'" + "}";
    }

}
