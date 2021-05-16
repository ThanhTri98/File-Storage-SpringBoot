package com.api.filestorage.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "videofile")
public class VideoFileEntity extends FilesEntity {
    private int length;

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

}
