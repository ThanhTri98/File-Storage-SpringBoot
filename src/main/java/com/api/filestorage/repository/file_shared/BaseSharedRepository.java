package com.api.filestorage.repository.file_shared;

import java.util.List;

import com.api.filestorage.entities.file_shared.BaseFileShared;

public interface BaseSharedRepository<T extends BaseFileShared> {
    List<? extends BaseFileShared> findByOwner(String owner);

    List<? extends BaseFileShared> findByReceiver(String receiver);

    void deleteById(int id);// tmp

    void insert(BaseFileShared file);
}
