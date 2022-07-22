package com.demo.multidbconnectionspringbootdemo.db1.repositories;

import com.demo.multidbconnectionspringbootdemo.db1.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}