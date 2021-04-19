package com.api.filestorage.repository;

import java.util.List;

import com.api.filestorage.entities.MusicFile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends JpaRepository<MusicFile, String> {

    @Query(value = "SELECT * FROM musicfile MF WHERE MF.CREATOR = :CREATOR AND MF.PARENT = :PARENT AND MF.EXTENSION IS NOT NULL", nativeQuery = true)
    List<MusicFile> findByFile(@Param("CREATOR") String creator, @Param("PARENT") String parent);
    @Query(value = "SELECT * FROM musicfile MF WHERE MF.CREATOR = :CREATOR AND MF.PARENT = :PARENT", nativeQuery = true)
    List<MusicFile> findAllFileInParent(@Param("CREATOR") String creator, @Param("PARENT") String parent);

}
