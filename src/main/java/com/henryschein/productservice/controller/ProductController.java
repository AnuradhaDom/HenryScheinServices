package com.henryschein.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.henryschein.productservice.model.Product;
import com.henryschein.productservice.service.ProductService;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/getProductById/{id}")
    public Product getProductById(@PathVariable int id) {
        return productService.getProductById(id).orElse(null);
    }

    @GetMapping("/getProductsByName")
    public Product getProductsByName(@RequestParam String name) {
        return productService.getProductsByname(name);
    }

    @GetMapping("/byPriceLessThan")
    public List<Product> getProductsByPriceLessThan(@RequestParam double price) {
        return productService.getProductsByPriceLessThan(price);
    }

    @GetMapping("/byPriceGreaterThan")
    public List<Product> getProductsByPriceGreaterThan(@RequestParam double price) {
        return productService.getProductsByPriceGreaterThan(price);
    }

    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PostMapping("/addProducts")
    public List<Product> addProducts(@RequestBody List<Product> products) {
        return productService.addProducts(products);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable int id) {
        return productService.deleteProduct(id);
    }

    @DeleteMapping("/deleteProducts")
    public String deleteProducts(@RequestParam List<Integer> ids) {
        return productService.deleteProducts(ids);
    }

    @DeleteMapping("/deleteProductByName")
    public String deleteProductByName(@RequestParam String name) {
        return productService.deleteProductByName(name);
    }

    @PutMapping("/updateProduct")
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @PutMapping("/updateProducts")
    public List<Product> updateProducts(@RequestBody List<Product> products) {
        return productService.updateProducts(products);
    }
}