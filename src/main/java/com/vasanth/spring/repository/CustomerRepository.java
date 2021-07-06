package com.vasanth.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vasanth.spring.model.Customers;

public interface CustomerRepository extends JpaRepository<Customers, Long> {

    List<Customers> findByName(String name);

    // Now we can use JpaRepository’s methods: save(), findOne(), findById(),
    // findAll(), count(), delete(), deleteById()… without implementing these
    // methods.
}
