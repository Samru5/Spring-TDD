package com.capgemini.springtdddemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.springtdddemo.model.Product;
import com.capgemini.springtdddemo.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService service;

	@PostMapping("/add")
	public Product add(){ 
		Product product = new Product(101, "Sugar");
		return product;

	}

	@DeleteMapping("/delete")
	public Product delete() {
		Product product = new Product(101, "Sugar");
		service.delete(product);
		return product;

	}

	@GetMapping("product/{id}")
	public Product readById(@PathVariable("id") int id) {
		Product product = service.read(id);
		return product;
	}

	@GetMapping("product/read/{id}")
	public ResponseEntity<Object> readByIdNotFound(@PathVariable("id") int id) {

		if (!(service.readWhenIdNotFound(id))) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("product/delete/{id}")
	public boolean deleteById(@PathVariable("id") int id) {
		return service.delete(id);
	}

	@DeleteMapping("product/del/{id}")
	public ResponseEntity<Object> deleteByIdNotFound(@PathVariable("id") int id) {

		if (!(service.delete(id))) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}

	@PutMapping("product/update/{id}")
	public ResponseEntity<Object>updateProduct(@RequestBody Product product,@PathVariable int id) {
		if(service.update(product))
		{
			product.setProductname(product.getProductname());
			return ResponseEntity.ok(product);
		}
		return ResponseEntity.notFound().build();
		
	}
	
}
