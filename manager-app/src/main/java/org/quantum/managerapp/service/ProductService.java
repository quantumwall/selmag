package org.quantum.managerapp.service;

import java.util.Collection;

import org.quantum.managerapp.model.Product;

public interface ProductService {

    Collection<Product> findAll();

    Product create(String title, String description);

    Product getProduct(Long id);
}
