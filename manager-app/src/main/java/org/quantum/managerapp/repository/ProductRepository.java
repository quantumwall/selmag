package org.quantum.managerapp.repository;

import java.util.Collection;

import org.quantum.managerapp.model.Product;

public interface ProductRepository {

    Collection<Product> findAll();

    Product save(Product product);
}
