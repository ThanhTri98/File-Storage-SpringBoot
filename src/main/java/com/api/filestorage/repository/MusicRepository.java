package com.api.filestorage.repository;

import java.util.List;

import com.api.filestorage.entities.MusicFile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MusicRepository extends JpaRepository<MusicFile, Integer> {

    @Query(value = "SELECT * FROM musicfile MF WHERE MF.CREATOR = :CREATOR AND MF.PARENT = :PARENT AND MF.EXTENSION IS NOT NULL", nativeQuery = true)
    List<MusicFile> findByFile(@Param("CREATOR") String creator, @Param("PARENT") String parent);

    @Query(value = "SELECT * FROM musicfile MF WHERE MF.CREATOR = :CREATOR AND MF.PARENT = :PARENT", nativeQuery = true)
    List<MusicFile> findAllFileInParent(@Param("CREATOR") String creator, @Param("PARENT") String parent);

    @Query(value = "SELECT 1 FROM musicfile MF WHERE MF.CREATOR = :CREATOR AND MF.PARENT = :PARENT AND MF.NAME = :NEWNAME", nativeQuery = true)
    Integer isDupplicateName(@Param("CREATOR") String creator, @Param("PARENT") String parent,
            @Param("NEWNAME") String newName);

    @Modifying
    @Transactional
    @Query(value = "UPDATE musicfile MF SET MF.NAME = :NEWNAME WHERE MF.ID = :ID", nativeQuery = true)
    void editNameFolder(@Param("ID") int id, @Param("NEWNAME") String newName);

}
