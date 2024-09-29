package org.quantum.managerapp.repository;

import java.util.Collection;
import java.util.Optional;

import org.quantum.managerapp.model.Product;

public interface ProductRepository {

    Collection<Product> findAll();

    Product save(Product product);
    
    Optional<Product> findById(Long id);
}
