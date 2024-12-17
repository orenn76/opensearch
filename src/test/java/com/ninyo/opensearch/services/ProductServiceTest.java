package com.ninyo.opensearch.services;

import com.ninyo.opensearch.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ProductServiceTest {

	private static final String INDEX_NAME = "productindex";
	private static final String PRODUCT_NAME = "productName";
	private static final String PRODUCT_DESCRIPTION = "productDescription";
	private static final Integer PRODUCT_AMOUNT = 10;

	@Autowired
	private ProductService productService;

	@Test
	public void createIndexTest() {
		productService.deleteIndex(INDEX_NAME);

		productService.createIndex(INDEX_NAME);

		assertTrue(productService.IndexExists(INDEX_NAME));

		productService.deleteIndex(INDEX_NAME);
	}

	@Test
	public void createProductTest() {
		Product product = Product.builder().name(PRODUCT_NAME)
				.description(PRODUCT_DESCRIPTION)
				.amount(PRODUCT_AMOUNT)
				.build();

		// if index doesn't exist, creating a product will also create an index
		String productId = productService.create(product);

		Product found = productService.findById(productId);
		assertEquals(found.getName(), PRODUCT_NAME);
		assertEquals(found.getDescription(), PRODUCT_DESCRIPTION);
		assertEquals(found.getAmount(), PRODUCT_AMOUNT);

		productService.delete(productId);
	}

}
