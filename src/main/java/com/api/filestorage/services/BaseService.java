package com.api.filestorage.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.api.filestorage.dto.FileMoveDTO;
import com.api.filestorage.entities.FilesEntity;
import com.api.filestorage.repository.BaseRepository;

import org.springframework.web.multipart.MultipartFile;

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
        if (filesModel.getType_copy_move() == 0) {
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

    // Copy
    List<FileMoveDTO.Data> copyFile(FileMoveDTO filesModel);

    default List<FileMoveDTO.Data> copyFile(FileMoveDTO filesModel, BaseRepository<T> repos) {

        String creator = filesModel.getCreator();
        String new_parent = filesModel.getNew_parent();
        if (filesModel.getType_copy_move() == 0) {
            List<FileMoveDTO.Data> dataDuplicate = new ArrayList<>();
            filesModel.getDatas().forEach(data -> {
                // System.out.println(data.toString());
                if (repos.findByStateAndCreatorAndParentAndExtensionAndName(DEFAULT_STATE, creator, new_parent,
                        data.getExtension(), data.getName()) != null) // Duplicate
                    dataDuplicate.add(data);
                else {
                    // Insert row moi y chan row hien tai chi khac moi ID
                    FilesEntity files = HelperClass.transferBaseToInstance(repos.findById(data.getId()));
                    files.setParent(new_parent);
                    repos.insert(files);
                }
            });
            return dataDuplicate;
        } else if (filesModel.getType_copy_move() == 1) { // ghi đè
            System.out.println("ghi đè");
            //
            filesModel.getDatas().forEach(data -> {
                FilesEntity filesEntity = repos.findByStateAndCreatorAndParentAndExtensionAndName(DEFAULT_STATE,
                        creator, new_parent, data.getExtension(), data.getName());
                // Delete file ở thư mục đích
                repos.delete(filesEntity);
                // Sao chép thằng hiện tại qua bển
                FilesEntity files = HelperClass.transferBaseToInstance(repos.findById(data.getId()));
                files.setParent(new_parent);
                repos.insert(files);
            });
        } else { // Tạo bản mới tên tăng theo stt
            System.out.println("tạo bản mới tên tăng theo stt");
            filesModel.getDatas().forEach(data -> {
                int count = repos.countFileDuplicate(1, creator, new_parent, data.getExtension(), "%" + data.getName());
                FilesEntity files = HelperClass.transferBaseToInstance(repos.findById(data.getId()));
                files.setName("(" + count + ") " + data.getName());
                files.setParent(new_parent);
                repos.insert(files);
            });
        }
        return null;
    }

    boolean uploadFile(MultipartFile file, String fileInfor);

    default boolean uploadFile(MultipartFile file, String fileInfor, BaseRepository<T> repos) {
        if (file.isEmpty())
            return false;
        try {
            String[] parent_creator = HelperClass.getParentAndCreatorFromURI(fileInfor);
            if (parent_creator == null)
                return false;

            // UUID save to DB
            String file_sk = UUID.randomUUID().toString();

            // Path save server
            String[] typeOrExtension = HelperClass.getPathAndExtension(file.getContentType());
            String path = typeOrExtension[0] + file_sk + "." + typeOrExtension[1];

            // Data
            byte[] bytes = file.getBytes();

            // Save server
            Files.write(Paths.get(path), bytes);

            // Save DB
            // repos
            long size = file.getSize();
            String extension = typeOrExtension[1];
            String name = file.getOriginalFilename();
            name = name.substring(0, name.lastIndexOf("."));
            String parent = parent_creator[0];
            String creator = parent_creator[1];

            // Check name is duplicate
            int count = repos.countFileDuplicate(1, creator, parent, extension, "%" + name);
            if (count > 0)
                name = "(" + count + ") " + name;
            // System.out.println(count);
            // System.out.println(typeOrExtension[0]);
            // System.out.println(size);
            // System.out.println(extension);
            // System.out.println(name);
            // System.out.println(parent);
            // System.out.println(creator);
            // System.out.println("\n\n\n");

            FilesEntity filesEntity = HelperClass.getFilesEntity(extension, path);
            filesEntity.setSize(size);
            filesEntity.setExtension(extension);
            filesEntity.setName(name);
            filesEntity.setParent(parent);
            filesEntity.setCreator(creator);
            filesEntity.setModifyDate(LocalDateTime.now());
            filesEntity.setState(1);
            filesEntity.setFile_sk(file_sk);
            repos.insert(filesEntity);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    List<? extends FilesEntity> findAllFolderInParent(String creator, String parent);

    default List<? extends FilesEntity> findAllFolderInParent(String creator, String parent, BaseRepository<T> repos) {
        if (parent == null)
            parent = "";
        return repos.findByStateAndCreatorAndParentAndExtension(DEFAULT_STATE, creator, parent, FOLDER_EXT);
    }

}
