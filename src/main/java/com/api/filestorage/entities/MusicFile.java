package com.api.filestorage.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "musicfile")
public class MusicFile extends Files {
	private int length;
	private String bitRate;

	public MusicFile() {
	}

	public MusicFile(int length, String bitRate) {
		this.length = length;
		this.bitRate = bitRate;
	}

	public int getLength() {
		return this.length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getBitRate() {
		return this.bitRate;
	}

	public void setBitRate(String bitRate) {
		this.bitRate = bitRate;
	}

}
