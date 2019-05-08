package com.capgemini.springtdddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.springtdddemo.model.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {
	

}
