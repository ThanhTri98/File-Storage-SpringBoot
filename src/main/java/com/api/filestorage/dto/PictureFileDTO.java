package com.api.filestorage.dto;

public class PictureFileDTO extends FilesDTO {
	private int width;
	private int height;

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
