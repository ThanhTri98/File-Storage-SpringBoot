package com.api.filestorage.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface BaseController<T> {

    @GetMapping(value = { "/{creator}", "/{creator/}", "/{creator}/{parent}" })
    List<T> findAllFileInParent(@PathVariable("creator") String creator, @PathVariable(required = false) String parent);

    @PutMapping("/name") // id, old_name, new_name, cur_parent, extension, creator
    ResponseEntity<?> editFilesName(@RequestBody Map<String, String> filesModel);

    // @PutMapping("/state") // id, name, parent, extension, creator ->>>> trash, untrash
    // ResponseEntity<?> editFilesState(@RequestBody Map<String, String> filesModel);

    // @PutMapping("/state") // id, new_parent ->>>> move
    // ResponseEntity<?> editFilesParent(@RequestBody Map<String, String> filesModel);

    // share, copy, download, upload, sort, paging, search, filter
    @PostMapping()
    ResponseEntity<?> createNewFolder(@RequestBody T folder);

}
