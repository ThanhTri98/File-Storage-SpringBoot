package com.api.filestorage.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "videofile")
public class VideoFileEntity extends FilesEntity {
    private long length;

    @ManyToMany(mappedBy = "videos_owner", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UserEntity> owners;

    @ManyToMany(mappedBy = "videos_receiver", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UserEntity> receivers;

    public long getLength() {
        return this.length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public List<UserEntity> getOwners() {
        return this.owners;
    }

    public void setOwners(List<UserEntity> owners) {
        this.owners = owners;
    }

    public List<UserEntity> getReceivers() {
        return this.receivers;
    }

    public void setReceivers(List<UserEntity> receivers) {
        this.receivers = receivers;
    }

}
