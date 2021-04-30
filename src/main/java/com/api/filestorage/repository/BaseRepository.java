package com.api.filestorage.repository;

import java.util.List;

public interface BaseRepository<T> {
    // Find all find in folder
    List<T> findByStateAndCreatorAndParent(int state, String creator, String parent);

    // Check file name is dupplicate in DB
    T findByStateAndCreatorAndParentAndExtensionAndName(int state, String creator, String parent, String extension,
            String name);
}
