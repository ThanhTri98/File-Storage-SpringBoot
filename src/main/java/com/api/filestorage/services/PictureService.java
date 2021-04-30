package com.api.filestorage.services;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.api.filestorage.entities.PictureFile;
import com.api.filestorage.repository.PictureRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureService implements BaseService<PictureFile> {
    @Autowired
    private PictureRepository pictureRepository;

    @Override
    public List<PictureFile> findAllFileInParent(String creator, String parent) {
        if (parent == null)
            parent = "";
        return pictureRepository.findByStateAndCreatorAndParent(DEFAULT_STATE, creator, parent);
    }

    @Override
    public boolean isDupplicateName(String creator, String parent, String name, String extensions) {
        // File: Signature is file name + extendsion
        return pictureRepository.findByStateAndCreatorAndParentAndExtensionAndName(DEFAULT_STATE, creator, parent,
                extensions, name) != null;
    }

    @Override
    public void editFilesName(String newName, String oldName, int id, String extension) {
        pictureRepository.editFilesName(id, newName);
        if (extension.equals(FOLDER_EXT)) // folder
            pictureRepository.editFilesName(oldName, newName);
    }

    @Override
    public void createNewFolder(PictureFile folder) {
        folder.setFile_sk(UUID.randomUUID().toString());
        folder.setModifyDate(LocalDate.now());
        pictureRepository.save(folder);
    }

}
