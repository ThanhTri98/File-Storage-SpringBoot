package com.api.filestorage.repository;

import java.util.List;

public interface BaseRepository<T> {
    List<T> findByCreatorAndParent(String creator, String parent);

    // check files name
    T findByCreatorAndParentAndExtensionAndName(String creator, String parent, String extension, String name);

    // check folder name
    T findByExtensionIsNullAndCreatorAndParentAndName(String creator, String parent, String name);
}
