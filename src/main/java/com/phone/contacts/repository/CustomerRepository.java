package com.phone.contacts.repository;

import com.phone.contacts.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
