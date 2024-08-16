package com.accenture.ms_customers.repository;

import com.accenture.ms_customers.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByDocument(String document);
    Optional<Customer> findByEmail(String email);

    @Modifying
    @Transactional
    void  deleteCustomerByDocument(String document);
    @Modifying
    @Transactional
    void  deleteCustomerByEmail(String email);

}
