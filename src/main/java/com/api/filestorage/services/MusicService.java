package com.api.filestorage.services;

import java.util.List;
import java.util.Map;

import com.api.filestorage.entities.Files;
import com.api.filestorage.entities.MusicFile;
import com.api.filestorage.repository.MusicRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MusicService implements BaseService<MusicFile> {
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
	public List<? extends Files> findAllFileInParent(String creator, String parent) {
		return BaseService.super.findAllFileInParent(creator, parent, musicRepository);
	}

	@Override
	public boolean createFolder(Files files) {
		return BaseService.super.createFolder(files, musicRepository);
	}

	@Override
	public void editFilesState(Map<String, String> filesModel) {
		BaseService.super.editFilesState(filesModel, musicRepository);
	}

	@Override
	public int editFilesParent(List<Map<String, String>> filesModels) {
		return BaseService.super.editFilesParent(filesModels, musicRepository);
	}

}
