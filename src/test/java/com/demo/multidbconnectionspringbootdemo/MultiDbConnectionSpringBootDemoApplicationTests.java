package com.demo.multidbconnectionspringbootdemo;

import com.demo.multidbconnectionspringbootdemo.db1.models.User;
import com.demo.multidbconnectionspringbootdemo.db1.repositories.UserRepository;
import com.demo.multidbconnectionspringbootdemo.db2.models.Product;
import com.demo.multidbconnectionspringbootdemo.db2.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@EnableTransactionManagement
@Slf4j
public class MultiDbConnectionSpringBootDemoApplicationTests {
 
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    @Transactional("db1TransactionManager")
    public void testUserCreated() {
        User user = new User();
        user.setName("John");
        user = userRepository.save(user);

        assertNotNull(userRepository.findAll());
        log.debug("User created: {}", user);
    }

    @Test
    @Transactional("db2TransactionManager")
    public void testProductCreated() {
        Product product = new Product();
        product.setName("Book");
        product.setId(2);
        product = productRepository.save(product);

        assertNotNull(productRepository.findAll());
        log.debug("Product created: {}", product);
    }
}