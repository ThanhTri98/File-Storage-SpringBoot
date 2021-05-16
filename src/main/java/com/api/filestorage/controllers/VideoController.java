package com.api.filestorage.controllers;

import java.util.List;
import java.util.Map;

import com.api.filestorage.dto.FileMoveDTO;
import com.api.filestorage.entities.FilesEntity;
import com.api.filestorage.entities.VideoFileEntity;
import com.api.filestorage.services.VideoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/videos")
public class VideoController implements BaseController<VideoFileEntity> {
	@Autowired
	private VideoService videoService;

	@Override
	public List<? extends FilesEntity> findAllFileInParent(String creator, String parent) {
		return BaseController.super.findAllFileInParent(creator, parent, videoService);
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
	public void editFilesState(Map<String, String> filesModel) {
		BaseController.super.editFilesState(filesModel, videoService);
	}

	@Override
	public ResponseEntity<?> editFilesParent(FileMoveDTO filesModels) {
		return BaseController.super.editFilesParent(filesModels, videoService);
	}

}
