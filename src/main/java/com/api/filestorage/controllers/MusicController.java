package com.api.filestorage.controllers;

import java.util.List;
import java.util.Map;

import com.api.filestorage.entities.Files;
import com.api.filestorage.entities.MusicFile;
import com.api.filestorage.services.MusicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/musics")
public class MusicController implements BaseController<MusicFile> {
	@Autowired
	private MusicService musicService;

	/*
	 * @Override public List<MusicFile> findAllFileInParent(@PathVariable("creator")
	 * String creator,
	 * 
	 * @PathVariable(required = false) String parent) { return
	 * musicService.findAllFileInParent(creator, parent); }
	 * 
	 * @Override public List<? extends Files> findAllFileInParent(String creator,
	 * String parent, Object services) { return
	 * BaseController.super.findAllFileInParent(creator, parent, services); }
	 */
	@Override
	public List<? extends Files> findAllFileInParent(String creator, String parent) {
		return BaseController.super.findAllFileInParent(creator, parent, musicService);
	}

	@Override // filesModel contain: id, old_name, new_name, cur_parent, extension, creator
	public ResponseEntity<?> editFilesName(Map<String, String> filesModel) {
		return BaseController.super.editFilesName(filesModel, musicService);
		/*
		 * if (!musicService.isDupplicateName(filesModel.get("creator"),
		 * filesModel.get("cur_parent"), filesModel.get("new_name"),
		 * filesModel.get("extension"))) {
		 * musicService.editFilesName(filesModel.get("new_name"),
		 * filesModel.get("old_name"), Integer.parseInt(filesModel.get("id")),
		 * filesModel.get("extension")); return new ResponseEntity<>(HttpStatus.OK); }
		 * return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		 */
	}

	// Create new folder
	@Override
	public ResponseEntity<?> createFolder(MusicFile folder) {
		return BaseController.super.createFolder(folder, musicService);
		/*
		 * if (!services.isDupplicateName(folder.getCreator(), folder.getParent(),
		 * folder.getName(), folder.getExtension())) { services.createNewFolder(folder);
		 * return new ResponseEntity<>(HttpStatus.OK); } return new
		 * ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		 */
	}

	@Override
	public void editFilesState(Map<String, String> filesModel) {
		BaseController.super.editFilesState(filesModel, musicService);
	}

	@Override
	public ResponseEntity<?> editFilesParent(List<Map<String, String>> filesModels) {
		return BaseController.super.editFilesParent(filesModels, musicService);
	}

}
