package com.henryschein.productservicetest;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import com.henryschein.productservice.controller.ProductController;
import com.henryschein.productservice.model.Product;
import com.henryschein.productservice.service.ProductService;
import com.henryschein.productservice.repository.ProductRepository; // Import the repository

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private ProductRepository productRepository; // Mock repository

    @InjectMocks
    private ProductController productController;

    private Product sampleProduct;

    @Before
    public void setUp() {
        // Sample product for testing
        sampleProduct = new Product();
        sampleProduct.setId(1);
        sampleProduct.setName("Test Product");
        sampleProduct.setPrice(10.0);
        sampleProduct.setDesc("Test Description");
        sampleProduct.setQuantity(5);
    }

    @Test
    public void testAddProduct() {
        // Setup behavior of productService mock
        when(productService.addProduct(any(Product.class))).thenReturn(sampleProduct);

        // Call the method being tested
        Product addedProduct = productController.addProduct(sampleProduct);

        // Assertions
        assertEquals(sampleProduct, addedProduct);
    }

    @Test
    public void testUpdateProduct() {
        // Setup behavior of productService mock
        when(productService.updateProduct(any(Product.class))).thenReturn(sampleProduct);

        // Call the method being tested
        Product updatedProduct = productController.updateProduct(sampleProduct);

        // Assertions
        assertEquals(sampleProduct, updatedProduct);
    }

    @Test
    public void testGetProductById() {
        // Setup behavior of productService mock
        when(productService.getProductById(1)).thenReturn(Optional.of(sampleProduct));

        // Call the method being tested
        Optional<Product> retrievedProductOptional = productService.getProductById(1);

        // Assertions
        assertEquals(sampleProduct, retrievedProductOptional.orElse(null));
    }

    @Test
    public void testDeleteProduct() {
        // Setup behavior of productService mock
        when(productService.deleteProduct(1)).thenReturn("Product removed successfully!1");

        // Call the method being tested
        String result = productController.deleteProduct(1);

        // Assertions
        assertEquals("Product removed successfully!1", result);
    }

    // Similarly, add test methods for other controller methods like getAllProducts, deleteProducts, etc.

}
