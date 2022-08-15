package com.epam.training.fooddelivery.data;

import com.epam.training.fooddelivery.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
