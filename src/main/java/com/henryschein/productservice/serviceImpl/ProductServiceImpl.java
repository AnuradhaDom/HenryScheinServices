package com.henryschein.productservice.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.henryschein.productservice.constant.ProductConstants;
import com.henryschein.productservice.customeexception.CustomException;
import com.henryschein.productservice.errorcodes.ErrorCodes;
import com.henryschein.productservice.model.Product;
import com.henryschein.productservice.repository.ProductRepository;
import com.henryschein.productservice.service.ProductService;
import com.henryschein.userservice.model.User;

import ch.qos.logback.classic.Logger;


@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	public static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
	


	@Override
	public List<Product> getAllProducts() {
		try {
			LOGGER.info(ProductConstants.PRODUCT_FETCHING);
			List<Product> products = productRepository.findAll();
	        LOGGER.info("successfully getAllProducts method executed {}", products);
	        return products;
	     } catch (Exception e) {
	         LOGGER.error("Error occurred while fetching products: {}" ,e);
	         throw new CustomException("Product not found with id: " + e);
	     }

}

	@Override
	public Optional<Product> getProductById(int id) {
		try {
            Product product = productRepository.findById(id).orElse(null);
            LOGGER.info("successfully getProductById method executed {}", product);
            return productRepository.findById(id);
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching product by Id: {}" ,e);
            throw new CustomException("Product not found with id: " + e);
        }
    }

	@Override
	public Product getProductsByname(String name) {
		try {
            Product product = productRepository.findByName(name);
            LOGGER.info("successfully getProductById method executed {}", product);
            return product;
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching product by name: {}" ,e);
            throw new CustomException("Product not found with name: " + e);
        }
	}

	@Override
	public List<Product> getProductsByPriceLessThan(double price) {
		try {
            List<Product> products = productRepository.findProductsByPriceLessThan(price);
            LOGGER.info("successfully getProductsByPriceLessThan method executed {}", products);
            return products;
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching product by PriceLessThan: {}" ,e);
            throw new CustomException("Product not found with PriceLessThan: " + e);
        }
	}

	@Override
	public List<Product> getProductsByPriceGreaterThan(double price) {
		try {
            List<Product> products = productRepository.findProductsByPriceGreaterThan(price);
            LOGGER.info("successfully getProductsByPriceGreaterThan method executed {}", products);
            return products;
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching product by PriceGreaterThan: {}" ,e);
            throw new CustomException("Product not found with PriceGreaterThan: " + e);
        }	}

	@Override
	public Product addProduct(Product product) {
		try {
            if (product.getName() == null || product.getName().isEmpty()) {
                String errorMessage = ProductConstants.INVALID_PRODUCT_NAME;
                LOGGER.info(errorMessage);
                throw new CustomException( "Product not added: " + errorMessage);
            }
            
        return productRepository.save(product);
    }
    catch(Exception e){
        LOGGER.error("Error occurred while creating product: {}", product , e );
        throw new CustomException("Product not added: " + e);
    }

}

	@Override
	public List<Product> addProducts(List<Product> products) {
		try {
			List<Product> addedProducts = new ArrayList<>();

            for (Product product : products) {
                if (product.getName() == null || product.getName().isEmpty()) {
                    String errorMessage = ProductConstants.INVALID_PRODUCT_NAME;
                    LOGGER.info(errorMessage);
                    throw new CustomException( "Product not added: " + errorMessage);
                }
                addedProducts.add(product);
            }
            return productRepository.saveAll(addedProducts);
        
		
		}
    catch(Exception e){
        LOGGER.error("Error occurred while creating products: {}", products , e );
        throw new CustomException("Products not added: " + e);
    }

	}

	@Override
	public String deleteProduct(int id) {
		try {
            Product product = productRepository.findById(id).orElse(null);
            productRepository.deleteById(id);
            LOGGER.info("Product Deleted {}", product);
            return "Product removed successfully!" + id;
        } catch (Exception e) {
            LOGGER.error("Error occurred while Deleting Product by Id: {}" ,e);
            throw new CustomException("Product not deleted: " + e);
        }
	}

	@Override
	public String deleteProducts(List<Integer> ids) {
		try {
            for (Integer id : ids) {
                Product product = productRepository.findById(id).orElse(null);
                productRepository.deleteById(product.getId());
                LOGGER.info("successfully deleteProducts method executed {}", product);

            }
            return "Products Deleted successfully !";
        } catch (Exception e) {
            LOGGER.error("Error occurred while Deleting Products by Id's: {}" ,e);
            throw new CustomException("Products not deleted: " + e);
        }
	}

	@Override
	public String deleteProductByName(String name) {
		try {
            
                Product product = productRepository.findByName(name);
                
                productRepository.deleteById(product.getId());
                LOGGER.info("successfully deleteProductByName method executed {}", product);
                
            
            return "Products Deleted successfully !";
            
        } catch (Exception e) {
            LOGGER.error("Error occurred while Deleting Products by Name: {}" ,e);
            throw new CustomException("Products not deleted: " + e);
        }
	}

	@Override
	public Product updateProduct(Product product) {
		try {
			Product existingProduct = productRepository.findById(product.getId()).orElse(null);
			existingProduct.setName(product.getName());
			existingProduct.setDesc(product.getDesc());
			existingProduct.setPrice(product.getPrice());
			existingProduct.setQuantity(product.getQuantity());
			Product updatedProduct = productRepository.save(existingProduct);
            LOGGER.info("UseProductr Updated {}", updatedProduct);
            return updatedProduct;
        } catch (Exception e) {
            LOGGER.error("Error occurred while Updating Product: {}" ,e);
            throw new RuntimeException("Failed to Update Product");
        }
	}

	@Override
	public List<Product> updateProducts(List<Product> products) {
		try {
            List<Product> updatedProducts = new ArrayList<>();
			for(Product product : products) {
			Product existingProduct = productRepository.findById(product.getId()).orElse(null);
			existingProduct.setName(product.getName());
			existingProduct.setDesc(product.getDesc());
			existingProduct.setPrice(product.getPrice());
			existingProduct.setQuantity(product.getQuantity());
			Product updatedProduct = productRepository.save(existingProduct);
			updatedProducts.add(updatedProduct);
            LOGGER.info("UseProductr Updated {}", updatedProduct);
            }
			return updatedProducts;
		}catch(Exception e) {
            LOGGER.error("Error occurred while Updating Products: {}" ,e);
            throw new RuntimeException("Failed to Update Products");
        }
	}

	}
