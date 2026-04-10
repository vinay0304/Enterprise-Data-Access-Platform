package com.enterprise.data.repository;

import com.enterprise.data.entity.DataAsset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataAssetRepository extends JpaRepository<DataAsset, Long> {
}
