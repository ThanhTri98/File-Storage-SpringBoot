package com.api.filestorage.repository;

import com.api.filestorage.entities.PictureFile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PictureRepository extends JpaRepository<PictureFile, Integer>, BaseRepository<PictureFile> {
    @Modifying
    @Query(value = "UPDATE picturefile PF SET PF.NAME = :NEWNAME, PF.MODIFY_DATE=NOW() WHERE PF.ID = :ID", nativeQuery = true)
    void editFilesName(@Param("ID") int id, @Param("NEWNAME") String newName);

    // update folder name
    @Modifying
    @Transactional
    @Query(value = "UPDATE picturefile PF SET PF.PARENT = :N_PARENT, PF.MODIFY_DATE=NOW() WHERE PF.PARENT = :O_PARENT", nativeQuery = true)
    void editFilesName(@Param("O_PARENT") String oldParent, @Param("N_PARENT") String newParent);
}
