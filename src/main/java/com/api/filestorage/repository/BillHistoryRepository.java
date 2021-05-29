package com.api.filestorage.repository;

import java.util.List;

import com.api.filestorage.entities.BillHistoryEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BillHistoryRepository extends JpaRepository<BillHistoryEntity, Integer> {
    // ADMIN
    @Query(value = "SELECT SUM(b.amount) FROM billhistory b", nativeQuery = true)
    long getTotalSalary();

    @Query(value = "SELECT SUM(b.amount) FROM billhistory b WHERE YEAR(b.CREATE_DATE)=?1 AND MONTH(b.CREATE_DATE)=?2", nativeQuery = true)
    Long getSalaryByMonthOfYear(int year, int month);

    @Query(value = "SELECT * FROM billhistory b WHERE b.USER_ID=?1", nativeQuery = true)
    List<BillHistoryEntity> findByUserId(String user_id);
}
