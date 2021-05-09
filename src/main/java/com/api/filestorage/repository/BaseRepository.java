package com.api.filestorage.repository;

import java.util.List;

import com.api.filestorage.entities.FilesEntity;

import org.springframework.lang.NonNull;

public interface BaseRepository<T> {
    // Find all find in folder
    List<? extends FilesEntity> findByStateAndCreatorAndParent(int state, String creator, String parent);

    // Check file name is dupplicate in DB
    T findByStateAndCreatorAndParentAndExtensionAndName(int state, String creator, String parent, String extension,
            String name);

    // <----------------EDIT NAME
    void editFilesName(int id, String newName); // tmp - file

    void editFilesName(String oldName, String newName); // tmp - folder
    // ---------------->EDIT NAME

    // <----------------EDIT STATE
    void editFilesState(int id, int state); // tmp - file

    void editFilesState(String name, int state, String creator); // tmp - folder
    // ---------------->EDIT STATE

    // <----------------MOVE
    void editFilesParent(int id, String parent); // tmp - files

    // ---------------->MOVE
    void insert(@NonNull FilesEntity files); // tmp

    void delete(@NonNull FilesEntity files); // tmp
}
