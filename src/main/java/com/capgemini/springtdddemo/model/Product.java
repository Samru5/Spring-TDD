package com.capgemini.springtdddemo.model;

import javax.persistence.*;

@Entity
public class Product {

	@Id
	private int productId;
	private String productname;

	public Product() {
		super();
	}

	public Product(int productId, String productname) {
		super();
		this.productId = productId;
		this.productname = productname;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productname=" + productname + "]";
	}

}
