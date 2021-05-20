package com.api.filestorage.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public abstract class FilesEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String file_sk;
	private String name;
	private long size;
	private String extension;
	private String parent;
	private String creator;
	@Column(name = "modify_date")
	private LocalDateTime modifyDate;
	private int state;

	public Integer getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSize() {
		return this.size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getExtension() {
		return this.extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getParent() {
		return this.parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getModifyDate() {
		// return this.modifyDate;
		return this.modifyDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}

	@JsonIgnore
	public LocalDateTime getDT() {
		return this.modifyDate;
	}

	public void setModifyDate(LocalDateTime modifyDate) {
		this.modifyDate = modifyDate;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFile_sk() {
		return this.file_sk;
	}

	public void setFile_sk(String file_sk) {
		this.file_sk = file_sk;
	}

	public int getState() {
		return this.state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
