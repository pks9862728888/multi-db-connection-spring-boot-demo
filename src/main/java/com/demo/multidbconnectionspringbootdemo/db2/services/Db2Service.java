package com.demo.multidbconnectionspringbootdemo.db2.services;

import com.demo.multidbconnectionspringbootdemo.db2.models.Product;
import com.demo.multidbconnectionspringbootdemo.db2.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Db2Service {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        return this.productRepository.save(product);
    }

    public Optional<Product> findProductBy(int productId) {
        return productRepository.findById(productId);
    }
}
