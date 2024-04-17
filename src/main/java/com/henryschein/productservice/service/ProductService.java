package com.henryschein.productservice.service;

import java.util.List;
import java.util.Optional;

import com.henryschein.productservice.model.Product;

public interface ProductService {
	
	//Get products
	public List<Product> getAllProducts();
	public Optional<Product> getProductById(int id);
	public Product getProductsByname(String name);
	public List<Product> getProductsByPriceLessThan(double price);
	public List<Product> getProductsByPriceGreaterThan(double price);
	
	//Add Products
	public Product addProduct(Product product);
	public List<Product> addProducts(List<Product> products);
	
	//Delete Product
	public String deleteProduct(int id);
	public String deleteProducts(List<Integer> ids);
	public String deleteProductByName(String name);

	
	//Update Product
	public Product updateProduct(Product product);
	public List<Product> updateProducts(List<Product> products);
	
	
	

}
