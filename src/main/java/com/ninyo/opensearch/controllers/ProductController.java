package com.ninyo.opensearch.controllers;

import com.ninyo.opensearch.models.Index;
import com.ninyo.opensearch.models.Product;
import com.ninyo.opensearch.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

	public static final String ID_PATTERN = "/{id}";
	
	private ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
	    this.productService = productService;
	}

	@PostMapping("/index")
	@ResponseStatus(HttpStatus.CREATED)
	public void createIndex(@RequestBody Index index) {
		productService.createIndex(index.getName());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public String create(@RequestBody Product product) {
		return productService.create(product);
	}
	
	@GetMapping(ID_PATTERN)
	@ResponseStatus(HttpStatus.OK)
	public Product findById(@PathVariable String id) {
		return productService.findById(id);
	}

}
