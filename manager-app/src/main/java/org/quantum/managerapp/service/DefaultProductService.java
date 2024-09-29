package org.quantum.managerapp.service;

import java.util.Collection;

import org.quantum.managerapp.exception.ProductNotFoundException;
import org.quantum.managerapp.model.Product;
import org.quantum.managerapp.repository.ProductRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DefaultProductService implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Collection<Product> findAll() {
	return productRepository.findAll();
    }

    @Override
    public Product create(String title, String details) {
	return productRepository.save(new Product(null, title, details));
    }

    @Override
    public Product getProduct(Long id) {
	return productRepository.findById(id)
		.orElseThrow(() -> new ProductNotFoundException("Product id %d not found".formatted(id)));
    }
}
