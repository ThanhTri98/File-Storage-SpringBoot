package com.api.filestorage.services;

import java.util.List;
import java.util.Map;

import com.api.filestorage.dto.FileMoveDTO;
import com.api.filestorage.entities.FilesEntity;
import com.api.filestorage.entities.VideoFileEntity;
import com.api.filestorage.repository.VideoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoService implements BaseService<VideoFileEntity> {
    @Autowired
    private VideoRepository videoRepository;

    @Override
    public List<? extends FilesEntity> findAllFileInParent(String creator, String parent) {
        return BaseService.super.findAllFileInParent(creator, parent, videoRepository);
    }

    @Override
    public boolean editFilesName(Map<String, String> filesModel) {
        return BaseService.super.editFilesName(filesModel, videoRepository);
    }

    @Override
    public boolean createFolder(FilesEntity files) {
        return BaseService.super.createFolder(files, videoRepository);
    }

    @Override
    public void editFilesState(Map<String, String> filesModel) {
        BaseService.super.editFilesState(filesModel, videoRepository);

    }

    @Override
    public List<FileMoveDTO.Data> editFilesParent(FileMoveDTO filesModel) {
        return BaseService.super.editFilesParent(filesModel, videoRepository);
    }

}
