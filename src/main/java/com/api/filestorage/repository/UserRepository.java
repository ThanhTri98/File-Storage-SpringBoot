package com.api.filestorage.repository;

import java.util.List;

import com.api.filestorage.entities.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    List<UserEntity> findAll();

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String userName);

    @Query(value = "SELECT U.USERNAME FROM user U WHERE U.EMAIL=?1", nativeQuery = true)
    String findUserNameByEmail(String email);

    // ADMIN
    @Query(value = "SELECT COUNT(U.USERNAME) FROM user U", nativeQuery = true)
    int getTotalUsers();
}
