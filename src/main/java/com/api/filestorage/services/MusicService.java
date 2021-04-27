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
	private MusicRepository filesRepository;

	public List<MusicFile> getAllFiles() {
		return filesRepository.findAll();
	}

	public List<MusicFile> findByFile(String creator, String parent) {
		return filesRepository.findByFile(creator, parent);
	}

	public List<MusicFile> findAllFileInParent(String creator, String parent) {
		if (parent != null)
			return filesRepository.findAllFileInParent(creator, parent);
		return filesRepository.findAllFileInParent(creator);
	}

	public boolean isDupplicateName(String creator, String parent, String newName) {
		if (parent != null)
			return filesRepository.isDupplicateName(creator, parent, newName) != null;
		return filesRepository.isDupplicateName(creator, newName) != null;
	}

	public void editFilesName(String newName, String oldName, int id, String extension) {
		filesRepository.editFilesName(id, newName);
		if (extension == null) // folder
			filesRepository.editFilesName(oldName, newName);
	}

	public void createNewFolder(MusicFile folder) {
		folder.setFile_sk(UUID.randomUUID().toString());
		folder.setModifyDate(LocalDate.now());
		filesRepository.save(folder);
	}
}
