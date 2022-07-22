package com.demo.multidbconnectionspringbootdemo.db2.repositories;

import com.demo.multidbconnectionspringbootdemo.db2.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}