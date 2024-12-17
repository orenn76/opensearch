package com.ninyo.opensearch.services;

import com.ninyo.opensearch.models.Product;
import com.ninyo.opensearch.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductService {

    private ProductRepository productRepository;

    private ElasticsearchOperations elasticsearchOperations;

    public ProductService(ProductRepository productRepository, ElasticsearchOperations elasticsearchOperations) {
        this.productRepository = productRepository;
        this.elasticsearchOperations = elasticsearchOperations;
    }

    public void createIndex(String indexName) {
        elasticsearchOperations.indexOps(IndexCoordinates.of(indexName)).create();
    }

    public void deleteIndex(String indexName) {
        elasticsearchOperations.indexOps(IndexCoordinates.of(indexName)).delete();
    }

    public boolean IndexExists(String indexName) {
        return elasticsearchOperations.indexOps(IndexCoordinates.of(indexName)).exists();
    }

    public String create(Product product) {
        product = productRepository.save(product);
        return product.getId();
    }

    public Product findById(String productId) {
        return productRepository.findById(productId).orElse(null);
    }

    public void delete(String productId) {
        productRepository.deleteById(productId);
    }

}
