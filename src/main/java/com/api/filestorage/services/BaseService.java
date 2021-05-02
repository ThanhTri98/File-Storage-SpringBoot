package com.api.filestorage.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.api.filestorage.entities.Files;
import com.api.filestorage.repository.BaseRepository;

public interface BaseService<T extends Files> {
    static final int DEFAULT_STATE = 1;
    static final String FOLDER_EXT = "FOLDER";

    List<? extends Files> findAllFileInParent(String creator, String parent);

    default List<? extends Files> findAllFileInParent(String creator, String parent, BaseRepository<T> repos) {
        if (parent == null)
            parent = "";
        return repos.findByStateAndCreatorAndParent(DEFAULT_STATE, creator, parent);
    }

    boolean editFilesName(Map<String, String> filesModel);

    // filesModel contain: id, old_name, new_name, cur_parent, extension, creator
    default boolean editFilesName(Map<String, String> filesModel, BaseRepository<T> repos) {
        String new_name = filesModel.get("new_name");
        String cur_parent = filesModel.get("cur_parent");
        String extension = filesModel.get("extension");
        String creator = filesModel.get("creator");
        // Check name is duplicate
        if (repos.findByStateAndCreatorAndParentAndExtensionAndName(DEFAULT_STATE, creator, cur_parent, extension,
                new_name) != null)
            return false;
        // ---->
        int id = Integer.parseInt(filesModel.get("id"));
        String old_name = filesModel.get("old_name");
        repos.editFilesName(id, new_name);
        if (extension.equals(FOLDER_EXT)) // folder
            repos.editFilesName(old_name, new_name);
        return true;
    }

    void editFilesState(Map<String, String> filesModel);

    default void editFilesState(Map<String, String> filesModel, BaseRepository<T> repos) {
        repos.editFilesState(Integer.parseInt(filesModel.get("id")), Integer.parseInt(filesModel.get("state")));
        if (filesModel.get("extension").equals(FOLDER_EXT))
            repos.editFilesState(filesModel.get("name"), Integer.parseInt(filesModel.get("state")),
                    filesModel.get("creator"));

    }

    boolean createFolder(Files files);

    default boolean createFolder(Files files, BaseRepository<T> repos) {
        // Check name is duplicate
        if (repos.findByStateAndCreatorAndParentAndExtensionAndName(DEFAULT_STATE, files.getCreator(),
                files.getParent(), files.getExtension(), files.getName()) != null)
            return false;
        // ----->
        files.setFile_sk(UUID.randomUUID().toString());
        files.setModifyDate(LocalDate.now());
        repos.insert(files);
        return true;
    }

    int editFilesParent(List<Map<String, String>> filesModels);

    default int editFilesParent(List<Map<String, String>> filesModels, BaseRepository<T> repos) {
        return 0;
    }
}
