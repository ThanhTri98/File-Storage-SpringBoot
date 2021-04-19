package com.api.filestorage.services;

import java.util.List;

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
		return filesRepository.findAllFileInParent(creator, parent);
	}

}
