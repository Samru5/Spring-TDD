package com.capgemini.springtdddemo.service;

import java.util.Optional;

import com.capgemini.springtdddemo.model.Product;

public interface ProductService {
	
	public Product add(Product product);
	public void delete(Product product);
	public Product read(int id);
	public boolean delete(int id);
	public boolean readWhenIdNotFound(int id);
	public boolean update(Product product);
	
	

}
