package com.api.filestorage.repository;

import com.api.filestorage.entities.Files;
import com.api.filestorage.entities.PictureFile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PictureRepository extends JpaRepository<PictureFile, Integer>, BaseRepository<PictureFile> {
    @Modifying
    @Query(value = "UPDATE picturefile PF SET PF.NAME = :NEWNAME, PF.MODIFY_DATE=NOW() WHERE PF.ID = :ID", nativeQuery = true)
    void editFilesName(@Param("ID") int id, @Param("NEWNAME") String newName);

    // update folder name
    @Modifying
    @Query(value = "UPDATE picturefile PF SET PF.PARENT = :N_PARENT, PF.MODIFY_DATE=NOW() WHERE PF.PARENT = :O_PARENT", nativeQuery = true)
    void editFilesName(@Param("O_PARENT") String oldParent, @Param("N_PARENT") String newParent);

    @Modifying
    default void insert(@NonNull Files files) {
        this.save((PictureFile) files);
    }
    // 20210502
    // EDIT FILES STATE
    @Modifying
    @Query(value = "UPDATE picturefile PF SET PF.STATE = ?2 WHERE PF.ID=?1",nativeQuery = true)
    void editFilesState(int id, int state);

    @Modifying
    @Query(value = "UPDATE picturefile PF SET PF.STATE = ?2 WHERE PF.parent=?1 AND PF.CREATOR=?3",nativeQuery = true)
    void editFilesState(String nameOfParent, int state, String creator);

    @Modifying
    @Query(value = "UPDATE picturefile PF SET PF.PARENT = ?2 WHERE PF.ID =?1", nativeQuery = true)
    void editFilesParent(int id, String parent);

}
