package org.quantum.managerapp.service;

import java.util.Collection;

import org.quantum.managerapp.exception.ProductNotFoundException;
import org.quantum.managerapp.model.Product;
import org.quantum.managerapp.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DefaultProductService implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultProductService.class);
    private final ProductRepository productRepository;

    @Override
    public Collection<Product> findAll() {
	logger.info("find all products");
	return productRepository.findAll();
    }

    @Override
    public Product create(String title, String details) {
	logger.info("create product {}, {}", title, details);
	return productRepository.save(new Product(null, title, details));
    }

    @Override
    public Product getProduct(Long id) {
	logger.info("find product: {}", id);
	return productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product id %d not found".formatted(id)));
    }

    @Override
    public void update(Long id, String title, String details) {
	productRepository.findById(id).ifPresentOrElse(product -> {
	    logger.info("update product {} with title: {}, details: {}", id, title, details);
	    product.setTitle(title);
	    product.setDetails(details);
	}, () -> new ProductNotFoundException("Product id%d not found".formatted(id)));
    }

    @Override
    public void delete(Long id) {
	productRepository.delete(id);
    }
}
