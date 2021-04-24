package com.api.filestorage.controllers;

import java.util.List;

import com.api.filestorage.entities.MusicFile;
import com.api.filestorage.services.MusicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/musics")
@CrossOrigin
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
	public List<MusicFile> findAllFileInParent(@PathVariable("creator") String creator,
			@PathVariable("parent") String parent) {
		return musicService.findAllFileInParent(creator, parent);
	}

	@PutMapping("/files/editname/")
	public ResponseEntity<?> editFolderName(@RequestBody MusicFile musicFile) {
		if (musicService.editFolderName(musicFile.getCreator(), musicFile.getParent(), musicFile.getName(),
				musicFile.getId())) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
