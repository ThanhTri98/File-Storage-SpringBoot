package com.api.filestorage.entities.file_shared;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseFileShared {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int file_id;
    private String owner;
    private String receiver;

    public int getId() {
        return this.id;
    }

    public int getFile_id() {
        return this.file_id;
    }

    public void setFile_id(int file_id) {
        this.file_id = file_id;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getReceiver() {
        return this.receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    // public BaseFileShared() {
    // }

    // public BaseFileShared(int file_id, String owner, String receiver) {
    //     this.file_id = file_id;
    //     this.owner = owner;
    //     this.receiver = receiver;
    // }

}
