package com.api.filestorage.services;

import java.util.List;
import java.util.Map;

import com.api.filestorage.dto.FileMoveDTO;
import com.api.filestorage.entities.FilesEntity;
import com.api.filestorage.entities.PictureFileEntity;
import com.api.filestorage.repository.PictureRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureService implements BaseService<PictureFileEntity> {
    @Autowired
    private PictureRepository pictureRepository;

    @Override
    public List<? extends FilesEntity> findAllFileInParent(String creator, String parent) {
        return BaseService.super.findAllFileInParent(creator, parent, pictureRepository);
    }

    @Override
    public boolean editFilesName(Map<String, String> filesModel) {
        return BaseService.super.editFilesName(filesModel, pictureRepository);
    }

    @Override
    public boolean createFolder(FilesEntity files) {
        return BaseService.super.createFolder(files, pictureRepository);
    }

    @Override
    public void editFilesState(Map<String, String> filesModel) {
        BaseService.super.editFilesState(filesModel, pictureRepository);

    }

    @Override
    public List<FileMoveDTO.Data> editFilesParent(FileMoveDTO filesModel) {
        return BaseService.super.editFilesParent(filesModel, pictureRepository);
    }

}
