package org.quantum.managerapp.repository;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.LongStream;

import org.quantum.managerapp.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryProductRepository implements ProductRepository {

    private final ConcurrentLinkedQueue<Product> products;

    public InMemoryProductRepository() {
	products = new ConcurrentLinkedQueue<>();
	LongStream
		.range(1, 5)
		.forEach(i -> products
			.add(new Product(i, "Product%d".formatted(i), "Details of product%d".formatted(i))));
    }

    @Override
    public Collection<Product> findAll() {
	return Collections.unmodifiableCollection(products);
    }

}
