package com.api.filestorage.controllers;

import java.util.List;

import com.api.filestorage.entities.MusicFile;
import com.api.filestorage.services.MusicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@GetMapping(value = { "/files/{creator}", "/files/{creator}/{parent}" })
	public List<MusicFile> findAllFileInParent(@PathVariable("creator") String creator,
			@PathVariable(required = false) String parent) {
		return musicService.findAllFileInParent(creator, parent);
	}

	// Edit folder name
	@PutMapping("/files/editname")
	public ResponseEntity<?> editFolderName(@RequestBody MusicFile musicFile) {
		if (!musicService.isDupplicateName(musicFile.getCreator(), musicFile.getParent(), musicFile.getName())) {
			musicService.editFolderName(musicFile.getName(), musicFile.getId());
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

	}

	// Create new folder
	@PostMapping("/files")
	public ResponseEntity<?> createNewFolder(@RequestBody MusicFile folder) {
		if (!musicService.isDupplicateName(folder.getCreator(), folder.getParent(), folder.getName())) {
			musicService.createNewFolder(folder);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
