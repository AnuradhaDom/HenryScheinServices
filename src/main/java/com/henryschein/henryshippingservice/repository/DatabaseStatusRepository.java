package com.henryschein.henryshippingservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.henryschein.henryshippingservice.model.DatabaseStatus;
@Repository
public interface DatabaseStatusRepository extends JpaRepository<DatabaseStatus, Long>{



}
