package com.api.filestorage.services;

import java.util.List;
import java.util.Map;

import com.api.filestorage.entities.Files;
import com.api.filestorage.entities.PictureFile;
import com.api.filestorage.repository.PictureRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureService implements BaseService<PictureFile> {
    @Autowired
    private PictureRepository pictureRepository;

    @Override
    public List<? extends Files> findAllFileInParent(String creator, String parent) {
        return BaseService.super.findAllFileInParent(creator, parent, pictureRepository);
    }

    @Override
    public boolean editFilesName(Map<String, String> filesModel) {
        return BaseService.super.editFilesName(filesModel, pictureRepository);
    }

    @Override
    public boolean createFolder(Files files) {
        return BaseService.super.createFolder(files, pictureRepository);
    }

    @Override
    public void editFilesState(Map<String, String> filesModel) {
        BaseService.super.editFilesState(filesModel, pictureRepository);

    }

    @Override
    public int editFilesParent(List<Map<String, String>> filesModels) {
        return BaseService.super.editFilesParent(filesModels, pictureRepository);
    }

}
