package com.capgemini.springtdddemo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.capgemini.springtdddemo.controller.ProductController;
import com.capgemini.springtdddemo.model.Product;
import com.capgemini.springtdddemo.service.ProductService;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringTddDemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ProductController controller;

	@MockBean
	private ProductService service;

	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@Test
	public void checkName() {
		assertEquals("Sugar", controller.add().getProductname());
	}

	@Test
	public void checkId() {
		assertEquals(101, controller.add().getProductId());
	}

	@Test
	public void check() {
		assertThat(service).isNotNull();
	}

	@Test
	public void forAdd() throws Exception {
		// mockMvc.perform(MockMvcRequestBuilders.post("/add")).andExpect(status().isCreated());
		// mockMvc.perform(post("/add")).andExpect(status().isCreated());
		Product product = new Product(101, "Sugar");

		mockMvc.perform(post("/add")).andExpect(MockMvcResultMatchers.jsonPath("$.productId", is(101)));
		mockMvc.perform(post("/add")).andExpect(MockMvcResultMatchers.jsonPath("$.productname", is("Sugar")));

	}

	@Test
	public void forDelete() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/delete")).andExpect(status().isOk());

	}

	@Test
	public void readById() throws Exception {

		Product product = new Product(101, "Sugar");
		when(service.read(101)).thenReturn(product);

		mockMvc.perform(get("/product/101")).andExpect(MockMvcResultMatchers.jsonPath("$.productId", is(101)));

	}

//	  @Test public void readByIdNotFound() throws Exception {
//		  
//		  doReturn(false).when(service).readWhenIdNotFound(125);
//			//mockMvc.perform(delete("/product/delete/154")).andExpect(status().isNotFound());
//			this.mockMvc.perform(get("/product/read/125")).andDo(print()).andExpect(status().isNotFound());
//
//	  }
//	 

	@Test
	public void deleteById() throws Exception {

		doReturn(true).when(service).delete(101);
		mockMvc.perform(delete("/product/delete/101")).andExpect(status().isOk());

	}

	@Test
	public void deleteWhenIdNotFound() throws Exception {
		doReturn(false).when(service).delete(154);
		// mockMvc.perform(delete("/product/delete/154")).andExpect(status().isNotFound());
		this.mockMvc.perform(delete("/product/del/154")).andDo(print()).andExpect(status().isNotFound());

	}

	@Test
	public void update() throws Exception
	{
		Product oProduct=new Product(101,"Sugar");
		Product nProduct=new Product(101,"Rice");
		doReturn(Optional.of(nProduct)).when(service).read(101);
		doReturn(true).when(service).update(any());
		this.mockMvc.perform(put("/update/product/101").contentType(MediaType.APPLICATION_JSON).content(asJsonString(oProduct)))
		

		
		
	}

}
