package com.api.filestorage.repository.file_shared;

import com.api.filestorage.entities.file_shared.BaseFileShared;
import com.api.filestorage.entities.file_shared.MusicFileSharedEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicSharedRepository
        extends JpaRepository<MusicFileSharedEntity, Integer>, BaseSharedRepository<MusicFileSharedEntity> {
    default void insert(BaseFileShared fileShared) {
        MusicFileSharedEntity s = new MusicFileSharedEntity();
        s.setFile_id(fileShared.getFile_id());
        s.setOwner(fileShared.getOwner());
        s.setReceiver(fileShared.getReceiver());
        this.save(s);
    }
}
