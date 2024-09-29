package org.quantum.managerapp.service;

import java.util.Collection;

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
    public Product create(String title, String description) {
	return productRepository.save(new Product(null, title, description));
    }
}
