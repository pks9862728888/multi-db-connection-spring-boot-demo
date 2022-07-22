package com.demo.multidbconnectionspringbootdemo.controllers;

import com.demo.multidbconnectionspringbootdemo.db2.models.Product;
import com.demo.multidbconnectionspringbootdemo.db2.services.Db2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/db2")
public class Db2Controller extends GenericExceptionHandlerController {

    @Autowired
    private Db2Service db2Service;

    @GetMapping("/create")
    public ResponseEntity<Product> createNewProduct() throws Exception {
        int productId = db2Service.createProduct(Product.builder()
                .name("product1")
                .build()).getId();
        Optional<Product> product = db2Service.findProductBy(productId);
        if (product.isEmpty()) {
            throw new Exception("No product found having userId: " + productId);
        } else {
            return new ResponseEntity<>(product.get(), HttpStatus.CREATED);
        }
    }

}
