package com.api.filestorage.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.api.filestorage.dto.FileMoveDTO;
import com.api.filestorage.entities.FilesEntity;
import com.api.filestorage.entities.VideoFileEntity;
import com.api.filestorage.services.VideoService;
import com.api.filestorage.services.ClazzData.DataShared;
import com.api.filestorage.services.ClazzData.TrashOrUnTrash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/user/videos")
public class VideoController implements BaseController<VideoFileEntity> {
	@Autowired
	private VideoService videoService;

	@Override
	public List<? extends FilesEntity> findAllFileInParent(int state, String creator, String parent) {
		return BaseController.super.findAllFileInParent(state, creator, parent, videoService);
	}

	@Override // filesModel contain: id, old_name, new_name, cur_parent, extension, creator
	public ResponseEntity<?> editFilesName(Map<String, String> filesModel) {
		return BaseController.super.editFilesName(filesModel, videoService);
	}

	// Create new folder
	@Override
	public ResponseEntity<?> createFolder(VideoFileEntity folder) {
		return BaseController.super.createFolder(folder, videoService);
	}

	@Override
	public void editFilesState(TrashOrUnTrash trash) {
		BaseController.super.editFilesState(trash, videoService);
	}

	@Override
	public ResponseEntity<?> editFilesParent(FileMoveDTO filesModels) {
		return BaseController.super.editFilesParent(filesModels, videoService);
	}

	@Override
	public ResponseEntity<?> uploadFile(MultipartFile file, HttpServletRequest request) {
		return BaseController.super.uploadFile(file, request.getRequestURI(), videoService);
	}

	@Override
	public List<? extends FilesEntity> findAllFolderInParent(String creator, String parent) {
		return BaseController.super.findAllFolderInParent(creator, parent, videoService);
	}

	@Override
	public ResponseEntity<?> copyFile(FileMoveDTO filesModel) {
		return BaseController.super.copyFile(filesModel, videoService);
	}

	@Override
	public List<DataShared> findAllFileInParent( String creator, String parent) {
		return BaseController.super.findAllFileInParent(creator, parent, videoService);
	}

}
