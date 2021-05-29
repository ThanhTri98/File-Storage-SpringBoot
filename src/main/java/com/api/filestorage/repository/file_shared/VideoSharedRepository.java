package com.api.filestorage.repository.file_shared;

import com.api.filestorage.entities.file_shared.BaseFileShared;
import com.api.filestorage.entities.file_shared.VideoFileSharedEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoSharedRepository
                extends JpaRepository<VideoFileSharedEntity, Integer>, BaseSharedRepository<VideoFileSharedEntity> {
        default void insert(BaseFileShared fileShared) {
                VideoFileSharedEntity s = new VideoFileSharedEntity();
                s.setFile_id(fileShared.getFile_id());
                s.setOwner(fileShared.getOwner());
                s.setReceiver(fileShared.getReceiver());
                this.save(s);
        }

}
