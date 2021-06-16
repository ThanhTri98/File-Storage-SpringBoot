package com.api.filestorage.services;

import java.util.List;
import java.util.Map;

import com.api.filestorage.dto.FileMoveDTO;
import com.api.filestorage.dto.FileMoveDTO.Data;
import com.api.filestorage.entities.FilesEntity;
import com.api.filestorage.entities.PictureFileEntity;
import com.api.filestorage.repository.PictureRepository;
import com.api.filestorage.services.ClazzData.DataShared;
import com.api.filestorage.services.ClazzData.TrashOrUnTrash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PictureService implements BaseService<PictureFileEntity> {
    @Autowired
    private PictureRepository pictureRepository;

    @Override
    public List<? extends FilesEntity> findAllFileInParent(int state,String creator, String parent) {
        return BaseService.super.findAllFileInParent(state,creator, parent, pictureRepository);
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
    public void editFilesState(TrashOrUnTrash trash) {
		BaseService.super.editFilesState(trash, pictureRepository);
	}

    @Override
	public boolean moveFile(FileMoveDTO filesModel) {
		return BaseService.super.moveFile(filesModel, pictureRepository);
	}

    @Override
    public boolean uploadFile(MultipartFile file, String fileInfor) {
        return BaseService.super.uploadFile(file, fileInfor, pictureRepository);
    }

    @Override
    public List<? extends FilesEntity> findAllFolderInParent(String creator, String parent) {
        return BaseService.super.findAllFolderInParent(creator, parent, pictureRepository);
    }

    @Override
	public List<Data> copyFile(FileMoveDTO filesModel) {
		return BaseService.super.copyFile(filesModel, pictureRepository);
	}

    @Override
	public List<DataShared> findAllFileInParent(String creator, String parent) {
		return BaseService.super.findAllFileInParent( creator, parent, "pictures", pictureRepository);
	}

}
