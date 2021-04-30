package com.api.filestorage.services;

import java.util.List;

public interface BaseService<T> {
    static final int DEFAULT_STATE = 1;
    static final String FOLDER_EXT = "FOLDER";

    List<T> findAllFileInParent(String creator, String parent);

    boolean isDupplicateName(String creator, String parent, String name, String extension);

    void editFilesName(String newName, String oldName, int id, String extension);

    void createNewFolder(T folder);
}
