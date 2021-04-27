package com.api.filestorage.controllers;

import java.util.List;
import java.util.Map;

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

	@GetMapping(value = { "/{creator}", "/{creator/}", "/{creator}/{parent}" })
	public List<MusicFile> findAllFileInParent(@PathVariable("creator") String creator,
			@PathVariable(required = false) String parent) {
		return musicService.findAllFileInParent(creator, parent);
	}

	// Edit folder name
	@PutMapping("/edit")
	public ResponseEntity<?> editFilesName(@RequestBody Map<String, String> fileModel) {
		if (!musicService.isDupplicateName(fileModel.get("creator"), fileModel.get("old_name"),
				fileModel.get("new_name"))) {
			musicService.editFilesName(fileModel.get("new_name"), fileModel.get("old_name"),
					Integer.parseInt(fileModel.get("id")), fileModel.get("extension"));
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

	}

	// Create new folder
	@PostMapping()
	public ResponseEntity<?> createNewFolder(@RequestBody MusicFile folder) {
		if (!musicService.isDupplicateName(folder.getCreator(), folder.getParent(), folder.getName())) {
			musicService.createNewFolder(folder);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
