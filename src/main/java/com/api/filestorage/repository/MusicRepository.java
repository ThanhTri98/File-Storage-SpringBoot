package com.api.filestorage.repository;

import com.api.filestorage.entities.MusicFile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MusicRepository extends JpaRepository<MusicFile, Integer>, BaseRepository<MusicFile> {
        // update file name
    @Modifying
    @Query(value = "UPDATE musicfile MF SET MF.NAME = :NEWNAME, MF.MODIFY_DATE=NOW() WHERE MF.ID = :ID", nativeQuery = true)
    void editFilesName(@Param("ID") int id, @Param("NEWNAME") String newName);

    // update folder name
    @Modifying
    @Transactional
    @Query(value = "UPDATE musicfile MF SET MF.PARENT = :N_PARENT, MF.MODIFY_DATE=NOW() WHERE MF.PARENT = :O_PARENT", nativeQuery = true)
    void editFilesName(@Param("O_PARENT") String oldParent, @Param("N_PARENT") String newParent);
}
