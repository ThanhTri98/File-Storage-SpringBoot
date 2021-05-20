package com.api.filestorage.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.api.filestorage.dto.FileMoveDTO;
import com.api.filestorage.entities.FilesEntity;
import com.api.filestorage.entities.MusicFileEntity;
import com.api.filestorage.services.MusicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/user/musics")
public class MusicController implements BaseController<MusicFileEntity> {
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
	public List<? extends FilesEntity> findAllFileInParent(String creator, String parent) {
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
	public ResponseEntity<?> createFolder(MusicFileEntity folder) {
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
	public ResponseEntity<?> editFilesParent(FileMoveDTO filesModels) {
		return BaseController.super.editFilesParent(filesModels, musicService);
	}

	@Override
	public ResponseEntity<?> uploadFile(MultipartFile file, HttpServletRequest request) {
		return BaseController.super.uploadFile(file,request.getRequestURI(), musicService);
	}

	@Override
	public List<? extends FilesEntity> findAllFolderInParent(String creator, String parent) {
		return BaseController.super.findAllFolderInParent(creator, parent, musicService);
	}

	@Override
	public ResponseEntity<?> copyFile(FileMoveDTO filesModel) {
		return BaseController.super.copyFile(filesModel, musicService);
	}
}
