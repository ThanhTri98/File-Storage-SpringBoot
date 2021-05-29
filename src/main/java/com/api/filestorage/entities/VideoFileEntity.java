package com.api.filestorage.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "videofile")
public class VideoFileEntity extends FilesEntity {
    private long length;

    public long getLength() {
        return this.length;
    }

    public void setLength(long length) {
        this.length = length;
    }


}
