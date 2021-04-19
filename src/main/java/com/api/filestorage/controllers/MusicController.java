package com.api.filestorage.controllers;

import java.util.List;

import com.api.filestorage.entities.MusicFile;
import com.api.filestorage.services.MusicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/musics")
public class MusicController {
	@Autowired
	private MusicService musicService;

	@GetMapping
	public List<MusicFile> getAllFiles() {
		return musicService.getAllFiles();
	}

	@GetMapping("/file/{creator}/{parent}")
	public List<MusicFile> findByFile(@PathVariable("creator") String creator, @PathVariable("parent") String parent) {
		return musicService.findByFile(creator, parent);
	}
	@GetMapping("/files/{creator}/{parent}")
	public List<MusicFile> findAllFileInParent(@PathVariable("creator") String creator, @PathVariable("parent") String parent) {
		return musicService.findAllFileInParent(creator, parent);
	}
}
