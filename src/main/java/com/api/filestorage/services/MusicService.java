package com.api.filestorage.services;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.api.filestorage.entities.MusicFile;
import com.api.filestorage.repository.MusicRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MusicService {
	@Autowired
	private MusicRepository musicRepository;

	public List<MusicFile> findAllFileInParent(String creator, String parent) {
		if (parent == null)
			parent = "";
		return musicRepository.findByCreatorAndParent(creator, parent);
	}

	public boolean isDupplicateName(String... params) {
		// File: Signature is file name + extendsion
		return params[3] == null
				? musicRepository.findByExtensionIsNullAndCreatorAndParentAndName(params[0], params[1],
						params[2]) != null
				: musicRepository.findByCreatorAndParentAndExtensionAndName(params[0], params[1], params[3],
						params[2]) != null;
	}

	public void editFilesName(String newName, String oldName, int id, String extension) {
		musicRepository.editFilesName(id, newName);
		if (extension == null) // folder
			musicRepository.editFilesName(oldName, newName);
	}

	public void createNewFolder(MusicFile folder) {
		folder.setFile_sk(UUID.randomUUID().toString());
		folder.setModifyDate(LocalDate.now());
		musicRepository.save(folder);
	}
}
