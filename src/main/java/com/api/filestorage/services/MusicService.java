package com.api.filestorage.services;

import java.util.List;
import java.util.Map;

import com.api.filestorage.dto.FileMoveDTO;
import com.api.filestorage.dto.FileMoveDTO.Data;
import com.api.filestorage.entities.FilesEntity;
import com.api.filestorage.entities.MusicFileEntity;
import com.api.filestorage.repository.MusicRepository;
import com.api.filestorage.services.ClazzData.DataShared;
import com.api.filestorage.services.ClazzData.TrashOrUnTrash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MusicService implements BaseService<MusicFileEntity> {
	@Autowired
	private MusicRepository musicRepository;

	// @Override
	// public boolean isDupplicateName(String creator, String parent, String name,
	// String extension) {
	// // File: Signature is file name + extendsion
	// return BaseService.super.isDupplicateName(creator, parent, name, extension,
	// musicRepository);
	// }

	@Override
	public boolean editFilesName(Map<String, String> filesModel) {
		return BaseService.super.editFilesName(filesModel, musicRepository);
	}

	// public void createFolder(MusicFile folder) {
	// folder.setFile_sk(UUID.randomUUID().toString());
	// folder.setModifyDate(LocalDate.now());
	// musicRepository.save(folder);
	// }

	@Override
	public List<? extends FilesEntity> findAllFileInParent(int state, String creator, String parent) {
		return BaseService.super.findAllFileInParent(state, creator, parent, musicRepository);
	}

	@Override
	public boolean createFolder(FilesEntity files) {
		return BaseService.super.createFolder(files, musicRepository);
	}

	@Override
	public void editFilesState(TrashOrUnTrash trash) {
		BaseService.super.editFilesState(trash, musicRepository);
	}

	@Override
	public boolean moveFile(FileMoveDTO filesModel) {
		return BaseService.super.moveFile(filesModel, musicRepository);
	}

	@Override
	public boolean uploadFile(MultipartFile file, String fileInfor) {
		return BaseService.super.uploadFile(file, fileInfor, musicRepository);
	}

	// 20210519
	@Override
	public List<? extends FilesEntity> findAllFolderInParent(String creator, String parent) {
		return BaseService.super.findAllFolderInParent(creator, parent, musicRepository);
	}

	@Override
	public List<Data> copyFile(FileMoveDTO filesModel) {
		return BaseService.super.copyFile(filesModel, musicRepository);
	}

	@Override
	public List<DataShared> findAllFileInParent(String creator, String parent) {
		return BaseService.super.findAllFileInParent(creator, parent, "musics", musicRepository);
	}

	@Override
	public void delete(TrashOrUnTrash trash) {
		BaseService.super.delete(trash, musicRepository);
	}

}
