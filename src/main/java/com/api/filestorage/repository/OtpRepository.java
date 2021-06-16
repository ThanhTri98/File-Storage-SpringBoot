package com.api.filestorage.repository;

import com.api.filestorage.entities.OtpEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepository extends JpaRepository<OtpEntity, Integer> {

    @Query(value = "SELECT 1 FROM otp_code where uuid=?1 and code = ?2 and expire_time > now()", nativeQuery = true)
    Integer validOtp(String uuid, int code);

    @Query(value = "SELECT 1 FROM otp_code where email=?1 and code = ?2 and expire_time > now()", nativeQuery = true)
    Integer validOtpEmail(String email, int code);

}
