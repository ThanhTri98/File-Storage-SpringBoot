package com.api.filestorage.repository;

import java.util.List;

import com.api.filestorage.entities.FilesEntity;
import com.api.filestorage.entities.MusicFileEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface MusicRepository extends JpaRepository<MusicFileEntity, Integer>, BaseRepository<MusicFileEntity> {

    // update file name
    @Modifying
    @Query(value = "UPDATE musicfile MF SET MF.NAME = :NEWNAME, MF.MODIFY_DATE=NOW() WHERE MF.ID = :ID", nativeQuery = true)
    void editFilesName(@Param("ID") int id, @Param("NEWNAME") String newName);

    // update folder name
    @Modifying
    @Query(value = "UPDATE musicfile MF SET MF.PARENT = :N_PARENT, MF.MODIFY_DATE=NOW() WHERE MF.PARENT = :O_PARENT", nativeQuery = true)
    void editFilesName(@Param("O_PARENT") String oldParent, @Param("N_PARENT") String newParent);

    @Modifying
    default void insert(@NonNull FilesEntity files) {
        this.save((MusicFileEntity) files);
    }

    // 20210502
    // EDIT FILES STATE
    @Modifying
    @Query(value = "UPDATE musicfile MF SET MF.STATE = ?2, MF.MODIFY_DATE=NOW() WHERE MF.ID=?1", nativeQuery = true)
    void editFilesState(int id, int state);

    @Modifying
    @Query(value = "UPDATE musicfile MF SET MF.STATE = ?2, MF.MODIFY_DATE=NOW() WHERE MF.PARENT=?1 AND MF.CREATOR=?3", nativeQuery = true)
    void editFilesState(String nameOfParent, int state, String creator);

    @Modifying
    @Query(value = "UPDATE musicfile MF SET MF.PARENT = ?2 WHERE MF.ID =?1", nativeQuery = true)
    void editFilesParent(int id, String parent);

    @Modifying
    default void delete(@NonNull FilesEntity files) {
        this.deleteById(files.getId());
    }

    // 20210519
    @Query(value = "SELECT COUNT(ID) FROM musicfile MF WHERE MF.STATE = ?1  and CREATOR =?2 AND PARENT=?3 AND EXTENSION = ?4 AND NAME LIKE ?5", nativeQuery = true)
    Integer countFileDuplicate(int state, String creator, String parent, String extension, String name);

    @Query(value = "SELECT SUM(MF.SIZE) FROM musicfile MF WHERE CREATOR =?1 AND EXTENSION<>?2", nativeQuery = true)
    Long getTotalSize(String creator, String extension);

    @Query(value = "SELECT * FROM musicfile MF WHERE MF.CREATOR =?1 AND MF.STATE=1 AND MF.EXTENSION<>'FOLDER' AND MF.NAME LIKE ?2 ", nativeQuery = true)
    List<MusicFileEntity> findSearch(String creator, String query);

    // ADMIN
    @Query(value = "SELECT SUM(MF.SIZE) FROM musicfile MF WHERE EXTENSION<>'FOLDER'", nativeQuery = true)
    Long getTotalSize();

    @Query(value = "SELECT COUNT(MF.ID) FROM musicfile MF WHERE EXTENSION<>'FOLDER'", nativeQuery = true)
    int getTotalNumberTypeFile();
}
