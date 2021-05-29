package com.api.filestorage.repository.file_shared;

import com.api.filestorage.entities.file_shared.BaseFileShared;
import com.api.filestorage.entities.file_shared.PictureFileSharedEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureSharedRepository
        extends JpaRepository<PictureFileSharedEntity, Integer>, BaseSharedRepository<PictureFileSharedEntity> {
    default void insert(BaseFileShared fileShared) {
        PictureFileSharedEntity s = new PictureFileSharedEntity();
        s.setFile_id(fileShared.getFile_id());
        s.setOwner(fileShared.getOwner());
        s.setReceiver(fileShared.getReceiver());
        this.save(s);
    }
}
