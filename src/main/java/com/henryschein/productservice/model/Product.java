package com.henryschein.productservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Data
@Table(name = "henryproduct")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	
	@NotBlank(message = "product Name is require")
	@Size(min = 2 , max = 100 , message = "Name must be between 2 and 100 characters")
	private String name;
	
    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private double price;
    
    @NotNull(message = "Description is required")
    @Size(max = 255, message = "Description must not exceed 255 characters")
    @Column(name= "description")
    private String desc;
    
    private int quantity;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
    
    
	

}
