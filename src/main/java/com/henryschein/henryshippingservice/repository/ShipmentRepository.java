package com.henryschein.henryshippingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.henryschein.henryshippingservice.model.Shipment;


@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

}
