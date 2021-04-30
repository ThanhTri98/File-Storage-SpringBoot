package com.api.filestorage.services;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.api.filestorage.entities.MusicFile;
import com.api.filestorage.repository.MusicRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MusicService implements BaseService<MusicFile> {
	@Autowired
	private MusicRepository musicRepository;

	@Override
	public List<MusicFile> findAllFileInParent(String creator, String parent) {
		if (parent == null)
			parent = "";
		return musicRepository.findByStateAndCreatorAndParent(DEFAULT_STATE, creator, parent);
	}

	@Override
	public boolean isDupplicateName(String creator, String parent, String name, String extensions) {
		// File: Signature is file name + extendsion
		return musicRepository.findByStateAndCreatorAndParentAndExtensionAndName(DEFAULT_STATE, creator, parent,
				extensions, name) != null;
	}

	@Override
	public void editFilesName(String newName, String oldName, int id, String extension) {
		musicRepository.editFilesName(id, newName);
		if (extension.equals(FOLDER_EXT)) // folder
			musicRepository.editFilesName(oldName, newName);
	}

	public void createNewFolder(MusicFile folder) {
		folder.setFile_sk(UUID.randomUUID().toString());
		folder.setModifyDate(LocalDate.now());
		musicRepository.save(folder);
	}
}
