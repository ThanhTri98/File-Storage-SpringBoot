package com.api.filestorage.repository;

import com.api.filestorage.entities.MusicFile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends JpaRepository<MusicFile, String> {

}
