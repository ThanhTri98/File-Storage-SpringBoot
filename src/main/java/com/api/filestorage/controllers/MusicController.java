package com.api.filestorage.controllers;

import java.util.List;
import java.util.Map;

import com.api.filestorage.entities.MusicFile;
import com.api.filestorage.services.MusicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/musics")
public class MusicController implements BaseController<MusicFile> {
	@Autowired
	private MusicService musicService;

	@Override
	public List<MusicFile> findAllFileInParent(@PathVariable("creator") String creator,
			@PathVariable(required = false) String parent) {
		return musicService.findAllFileInParent(creator, parent);
	}

	// Edit folder name
	@Override
	public ResponseEntity<?> editFilesName(@RequestBody Map<String, String> filesModel) {
		if (!musicService.isDupplicateName(filesModel.get("creator"), filesModel.get("cur_parent"),
				filesModel.get("new_name"), filesModel.get("extension"))) {
			musicService.editFilesName(filesModel.get("new_name"), filesModel.get("old_name"),
					Integer.parseInt(filesModel.get("id")), filesModel.get("extension"));
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

	}

	// Create new folder
	@Override
	public ResponseEntity<?> createNewFolder(@RequestBody MusicFile folder) {
		if (!musicService.isDupplicateName(folder.getCreator(), folder.getParent(), folder.getName(),
				folder.getExtension())) {
			musicService.createNewFolder(folder);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
