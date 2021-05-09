package com.api.filestorage.dto;

public class MusicFileDTO extends FilesDTO {
	private int length;
	private String bitRate;

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
