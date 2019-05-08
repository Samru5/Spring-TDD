package com.capgemini.springtdddemo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.springtdddemo.dao.ProductDao;
import com.capgemini.springtdddemo.model.Product;
import com.capgemini.springtdddemo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao dao;

	@Override
	public Product add(Product product) {
		return dao.save(product);
	}

	@Override
	public void delete(Product product) {
		dao.deleteAll();

	}

	@Override
	public Product read(int id) {

		return dao.findById(id).get();

	}

	@Override
	public boolean delete(int id) {
		dao.deleteById(id);
		return true;
	}

	@Override
	public boolean readWhenIdNotFound(int id) {
		dao.findById(id);
		return true;
	}

	@Override
	public boolean update(Product product) {
		Optional<Product> p = dao.findById(product.getProductId());
		if (p.isPresent()) {
			dao.save(product);
			return true;

		}
		return false;
	}

}
