package com.api.filestorage.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.api.filestorage.dto.FileMoveDTO;
import com.api.filestorage.entities.FilesEntity;
import com.api.filestorage.services.BaseService;
import com.api.filestorage.services.ClazzData.DataShared;
import com.api.filestorage.services.ClazzData.TrashOrUnTrash;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface BaseController<T extends FilesEntity> {

    @GetMapping(value = { "/{state}/{creator}", "/{state}/{creator/}", "/{state}/{creator}/{parent}" })
    List<? extends FilesEntity> findAllFileInParent(@PathVariable int state, @PathVariable("creator") String creator,
            @PathVariable(required = false) String parent);

    default List<? extends FilesEntity> findAllFileInParent(int state, String creator, String parent,
            @NonNull BaseService<T> services) {
        return services.findAllFileInParent(state, creator, parent);
    }

    @GetMapping(value = { "/shared/{creator}", "/shared/{creator/}", "/shared/{creator}/{parent}" })
    List<DataShared> findAllFileInParent(@PathVariable("creator") String creator,
            @PathVariable(required = false) String parent);

    default List<DataShared> findAllFileInParent(String creator, String parent, @NonNull BaseService<T> services) {
        return services.findAllFileInParent(creator, parent);
    }

    @PutMapping("/name")
    ResponseEntity<?> editFilesName(@RequestBody Map<String, String> filesModel);

    // filesModel contain: id, old_name, new_name, cur_parent, extension, creator
    default ResponseEntity<?> editFilesName(Map<String, String> filesModel, @NonNull BaseService<T> services) {
        if (services.editFilesName(filesModel))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping
    ResponseEntity<?> createFolder(@RequestBody T folder);

    default ResponseEntity<?> createFolder(T folder, @NonNull BaseService<T> services) {
        if (services.createFolder(folder))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/trash") // id, [name, extension], state, creator ->>>> trash, untrash
    void editFilesState(@RequestBody TrashOrUnTrash trash);

    default void editFilesState(TrashOrUnTrash trash, @NonNull BaseService<T> services) {
        services.editFilesState(trash);
    }

    @PutMapping("/move") // id, [name, extension], new_parent, creator ->>>> move
    ResponseEntity<?> editFilesParent(@RequestBody FileMoveDTO filesModel);

    default ResponseEntity<?> editFilesParent(FileMoveDTO filesModel, @NonNull BaseService<T> services) {
        if (filesModel.getType_copy_move() == 0) {
            List<FileMoveDTO.Data> dataDuplicate = services.editFilesParent(filesModel);
            // dataDuplicate.forEach(d -> System.out.printf("%s, %s", d.getExtension(),
            // d.getName()));
            if (dataDuplicate.size() != 0) {
                Map<String, Object> responseData = new HashMap<>();
                responseData.put("msg", "Thư mục đích có " + dataDuplicate.size() + " mục trùng tên");
                filesModel.setDatas(dataDuplicate);
                responseData.put("data", filesModel);
                return new ResponseEntity<Map<String, Object>>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else { // Replace: Thay đổi file bị trùng bằng file trùng đồng thời xóa file bị trùng
            services.editFilesParent(filesModel);
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }

    // share, copy, download, upload, sort, paging, search, filter

    @PostMapping(value = { "/upload/{*}/{*}", "/upload/{*}/" })
    ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request);

    default ResponseEntity<?> uploadFile(MultipartFile file, String fileInfor, @NonNull BaseService<T> services) {
        if (services.uploadFile(file, fileInfor))
            return ResponseEntity.ok().body("Done");
        return ResponseEntity.badRequest().body("FAIL");

    }

    // 20200519
    @GetMapping(value = { "/fo/{creator}", "/fo/{creator/}", "/fo/{creator}/{parent}" })
    List<? extends FilesEntity> findAllFolderInParent(@PathVariable("creator") String creator,
            @PathVariable(required = false) String parent);

    default List<? extends FilesEntity> findAllFolderInParent(String creator, String parent,
            @NonNull BaseService<T> services) {
        return services.findAllFolderInParent(creator, parent);
    }

    // copy
    @PutMapping("/copy") // id, [name, extension], new_parent, creator ->>>> move
    ResponseEntity<?> copyFile(@RequestBody FileMoveDTO filesModel);

    default ResponseEntity<?> copyFile(FileMoveDTO filesModel, @NonNull BaseService<T> services) {
        if (filesModel.getType_copy_move() == 0) {
            List<FileMoveDTO.Data> dataDuplicate = services.copyFile(filesModel);
            // dataDuplicate.forEach(d -> System.out.printf("%s, %s", d.getExtension(),
            // d.getName()));
            if (dataDuplicate.size() != 0) {
                Map<String, Object> responseData = new HashMap<>();
                responseData.put("msg", "Thư mục đích có " + dataDuplicate.size() + " mục trùng tên");
                filesModel.setDatas(dataDuplicate);
                responseData.put("data", filesModel);
                return new ResponseEntity<Map<String, Object>>(responseData, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } else { // 2 || 1
            services.copyFile(filesModel);
            return new ResponseEntity<>("Done", HttpStatus.OK);
        }
    }

}
