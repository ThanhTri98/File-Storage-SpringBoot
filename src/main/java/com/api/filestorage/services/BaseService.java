package com.api.filestorage.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.api.filestorage.dto.FileMoveDTO;
import com.api.filestorage.entities.FilesEntity;
import com.api.filestorage.repository.BaseRepository;

public interface BaseService<T extends FilesEntity> {
    static final int DEFAULT_STATE = 1;
    static final String FOLDER_EXT = "FOLDER";

    List<? extends FilesEntity> findAllFileInParent(String creator, String parent);

    default List<? extends FilesEntity> findAllFileInParent(String creator, String parent, BaseRepository<T> repos) {
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

    boolean createFolder(FilesEntity files);

    default boolean createFolder(FilesEntity files, BaseRepository<T> repos) {
        // Check name is duplicate
        if (repos.findByStateAndCreatorAndParentAndExtensionAndName(DEFAULT_STATE, files.getCreator(),
                files.getParent(), files.getExtension(), files.getName()) != null)
            return false;
        // ----->
        files.setFile_sk(UUID.randomUUID().toString());
        files.setModifyDate(LocalDateTime.now());
        repos.insert(files);
        return true;
    }

    List<FileMoveDTO.Data> editFilesParent(FileMoveDTO filesModel);

    default List<FileMoveDTO.Data> editFilesParent(FileMoveDTO filesModel, BaseRepository<T> repos) {
        List<FileMoveDTO.Data> dataDuplicate = new ArrayList<>();
        String creator = filesModel.getCreator();
        String new_parent = filesModel.getNew_parent();
        if (!filesModel.getIs_replace()) {
            filesModel.getDatas().forEach(data -> {
                if (repos.findByStateAndCreatorAndParentAndExtensionAndName(DEFAULT_STATE, creator, new_parent,
                        data.getExtension(), data.getName()) != null) // Duplicate
                    dataDuplicate.add(data);
                else
                    repos.editFilesParent(data.getId(), new_parent);

            });
        } else { // Replace: Thay đổi file bị trùng bằng file trùng đồng thời xóa file bị trùng
            filesModel.getDatas().forEach(data -> {
                // Nếu là thư mục
                // ++ Vào độ sâu 1 tiếp tục kiểm tra các file và thư mục con

                // Nếu là file
                FilesEntity f = repos.findByStateAndCreatorAndParentAndExtensionAndName(DEFAULT_STATE, creator,
                        new_parent, data.getExtension(), data.getName());
                repos.editFilesParent(data.getId(), new_parent); // Thay đổi file bị trùng bằng file trùng
                repos.delete(f); // xóa file bị trùng
            });
        }
        return dataDuplicate;
    }
}
