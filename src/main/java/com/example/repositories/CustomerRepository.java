package com.example.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.enitities.Customer;

public interface CustomerRepository extends JpaRepository <Customer, Long>, JpaSpecificationExecutor<Customer>{

    List<Customer> findByLastName(String lastName);
    List<Customer> findAll();
	Customer findById(long id);
	
}