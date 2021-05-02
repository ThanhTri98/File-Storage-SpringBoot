package com.api.filestorage.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.api.filestorage.entities.Files;
import com.api.filestorage.services.BaseService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface BaseController<T extends Files> {

    @GetMapping(value = { "/{creator}", "/{creator/}", "/{creator}/{parent}" })
    List<? extends Files> findAllFileInParent(@PathVariable("creator") String creator,
            @PathVariable(required = false) String parent);

    default List<? extends Files> findAllFileInParent(String creator, String parent, @NonNull BaseService<T> services) {
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
    ResponseEntity<?> editFilesParent(@RequestBody List<Map<String, String>> filesModels);

    default ResponseEntity<?> editFilesParent(List<Map<String, String>> filesModels, @NonNull BaseService<T> services) {
        // int fileCopied = services.editFilesParent(filesModels);
        int fileCopied = -1;
        if (fileCopied != filesModels.size()) {
            Map<String, Object> resData = new HashMap<>();
            resData.put("msg", "Thư mục đích có " + (filesModels.size() - fileCopied) + " mục trùng tên");
            resData.put("key", Arrays.asList("1", "2", "2"));
            return new ResponseEntity<Map<String, Object>>(resData, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        filesModels.forEach(map -> {
            map.toString();
            map.values().forEach(v -> System.out.println(v));
        });
        return new ResponseEntity<String>("Sao chép thành công " + filesModels.size() + " FILES", HttpStatus.OK);
    }
    // share, copy, download, upload, sort, paging, search, filter

}
