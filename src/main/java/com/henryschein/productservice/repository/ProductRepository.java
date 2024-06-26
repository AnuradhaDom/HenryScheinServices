package com.henryschein.productservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.henryschein.productservice.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product ,Integer> {

	Product findByName(String name);

	List<Product> findProductsByPriceLessThan(double price);

	List<Product> findProductsByPriceGreaterThan(double price);

	

}
