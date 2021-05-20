package com.api.filestorage.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.api.filestorage.dto.FileMoveDTO;
import com.api.filestorage.entities.FilesEntity;
import com.api.filestorage.entities.PictureFileEntity;
import com.api.filestorage.services.PictureService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/user/pictures")
public class PictureController implements BaseController<PictureFileEntity> {
	@Autowired
	private PictureService pictureService;

	@Override
	public List<? extends FilesEntity> findAllFileInParent(String creator, String parent) {
		return BaseController.super.findAllFileInParent(creator, parent, pictureService);
	}

	@Override
	public ResponseEntity<?> editFilesName(Map<String, String> filesModel) {
		return BaseController.super.editFilesName(filesModel, pictureService);
	}

	@Override
	public ResponseEntity<?> createFolder(PictureFileEntity folder) {
		return BaseController.super.createFolder(folder, pictureService);
	}

	@Override
	public void editFilesState(Map<String, String> filesModel) {
		BaseController.super.editFilesState(filesModel, pictureService);
	}

	@Override
	public ResponseEntity<?> editFilesParent(FileMoveDTO filesModels) {
		return BaseController.super.editFilesParent(filesModels, pictureService);
	}

	@Override
	public ResponseEntity<?> uploadFile(MultipartFile file, HttpServletRequest request) {
		return BaseController.super.uploadFile(file,request.getRequestURI(), pictureService);
	}
	@Override
	public List<? extends FilesEntity> findAllFolderInParent(String creator, String parent) {
		return BaseController.super.findAllFolderInParent(creator, parent, pictureService);
	}
	@Override
	public ResponseEntity<?> copyFile(FileMoveDTO filesModel) {
		return BaseController.super.copyFile(filesModel, pictureService);
	}
}
