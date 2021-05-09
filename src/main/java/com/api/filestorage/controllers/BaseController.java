package com.api.filestorage.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.api.filestorage.dto.FileMoveDTO;
import com.api.filestorage.entities.FilesEntity;
import com.api.filestorage.services.BaseService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface BaseController<T extends FilesEntity> {

    @GetMapping(value = { "/{creator}", "/{creator/}", "/{creator}/{parent}" })
    List<? extends FilesEntity> findAllFileInParent(@PathVariable("creator") String creator,
            @PathVariable(required = false) String parent);

    default List<? extends FilesEntity> findAllFileInParent(String creator, String parent, @NonNull BaseService<T> services) {
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

    @PutMapping("/state") // id, [name, extension], state, creator ->>>> trash, untrash
    void editFilesState(@RequestBody Map<String, String> filesModel);

    default void editFilesState(Map<String, String> filesModel, @NonNull BaseService<T> services) {
        services.editFilesState(filesModel);
    }

    @PutMapping("/move") // id, [name, extension], new_parent, creator ->>>> move
    ResponseEntity<?> editFilesParent(@RequestBody FileMoveDTO filesModel);

    default ResponseEntity<?> editFilesParent(FileMoveDTO filesModel, @NonNull BaseService<T> services) {
        if (!filesModel.getIs_replace()) {
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

}
