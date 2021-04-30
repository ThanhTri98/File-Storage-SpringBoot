package com.api.filestorage.controllers;

import java.util.List;
import java.util.Map;

import com.api.filestorage.entities.PictureFile;
import com.api.filestorage.services.PictureService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pictures")
public class PictureController implements BaseController<PictureFile> {
	@Autowired
	private PictureService pictureService;

	@Override
	public List<PictureFile> findAllFileInParent(@PathVariable("creator") String creator,
			@PathVariable(required = false) String parent) {
		return pictureService.findAllFileInParent(creator, parent);
	}

	// Edit folder name
	@Override
	public ResponseEntity<?> editFilesName(@RequestBody Map<String, String> filesModel) {
		if (!pictureService.isDupplicateName(filesModel.get("creator"), filesModel.get("cur_parent"),
				filesModel.get("new_name"), filesModel.get("extension"))) {
			pictureService.editFilesName(filesModel.get("new_name"), filesModel.get("old_name"),
					Integer.parseInt(filesModel.get("id")), filesModel.get("extension"));
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

	}

	// Create new folder
	@Override
	public ResponseEntity<?> createNewFolder(@RequestBody PictureFile folder) {
		if (!pictureService.isDupplicateName(folder.getCreator(), folder.getParent(), folder.getName(),
				folder.getExtension())) {
			pictureService.createNewFolder(folder);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
