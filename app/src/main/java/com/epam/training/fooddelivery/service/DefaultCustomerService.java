package com.epam.training.fooddelivery.service;

import com.epam.training.fooddelivery.data.CustomerRepository;
import com.epam.training.fooddelivery.domain.Customer;
import com.epam.training.fooddelivery.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultCustomerService implements CustomerService {
    CustomerRepository customerRepository;

    public DefaultCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public Customer authenticate(User user) {
        for(Customer customer: customerRepository.findAll()) {
            if(customer.getEmail().equals(user.getEmail()) &&
                    customer.getPassword().equals(user.getPassword())) {
                return customer;
            }
        }
        throw new AuthenticationException("Invalid data");
    }
}
