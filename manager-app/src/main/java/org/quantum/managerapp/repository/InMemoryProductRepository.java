package org.quantum.managerapp.repository;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.quantum.managerapp.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryProductRepository implements ProductRepository {

    private static final Logger logger = LoggerFactory.getLogger(InMemoryProductRepository.class);
    private final ConcurrentLinkedQueue<Product> products;

    public InMemoryProductRepository() {
	products = new ConcurrentLinkedQueue<>();
    }

    @Override
    public Collection<Product> findAll() {
	return Collections.unmodifiableCollection(products);
    }

    @Override
    public Product save(Product product) {
	Long id = products.stream().mapToLong(Product::getId).max().orElse(0) + 1;
	product.setId(id);
	logger.info("Saving product: {}", product);
	products.add(product);
	return product;
    }

    @Override
    public Optional<Product> findById(Long id) {
	return products.stream().filter(product -> Objects.equals(product.getId(), id)).findAny();
    }

}
