package com.api.filestorage.repository;

import com.api.filestorage.entities.AccountPackageEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountPackageRepository extends JpaRepository<AccountPackageEntity, Integer> {
    AccountPackageEntity findByPrice(int price);
}
