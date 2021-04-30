package com.api.filestorage.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "picturefile")
public class PictureFile extends Files {

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
