package com.api.filestorage.controllers;

import java.util.List;
import java.util.Map;

import com.api.filestorage.entities.Files;
import com.api.filestorage.entities.PictureFile;
import com.api.filestorage.services.PictureService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pictures")
public class PictureController implements BaseController<PictureFile> {
	@Autowired
	private PictureService pictureService;

	@Override
	public List<? extends Files> findAllFileInParent(String creator, String parent) {
		return BaseController.super.findAllFileInParent(creator, parent, pictureService);
	}

	@Override
	public ResponseEntity<?> editFilesName(Map<String, String> filesModel) {
		return BaseController.super.editFilesName(filesModel, pictureService);
	}

	@Override
	public ResponseEntity<?> createFolder(PictureFile folder) {
		return BaseController.super.createFolder(folder, pictureService);
	}

	@Override
	public void editFilesState(Map<String, String> filesModel) {
		BaseController.super.editFilesState(filesModel, pictureService);
	}

	@Override
	public ResponseEntity<?> editFilesParent(List<Map<String, String>> filesModels) {
		return BaseController.super.editFilesParent(filesModels, pictureService);
	}
}
