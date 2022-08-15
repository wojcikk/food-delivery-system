package com.epam.training.fooddelivery.service;

import com.epam.training.fooddelivery.repository.CustomerRepository;
import com.epam.training.fooddelivery.repository.OrderItemRepository;
import com.epam.training.fooddelivery.repository.OrderRepository;
import com.epam.training.fooddelivery.domain.Cart;
import com.epam.training.fooddelivery.domain.Customer;
import com.epam.training.fooddelivery.domain.Order;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DefaultOrderService implements OrderService {
    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;
    private CustomerRepository customerRepository;

    public DefaultOrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public Order createOrder(Customer customer) {

        Cart cart = customer.getCart();

        if(customer.getCart().getOrderItems().isEmpty()) {
            throw new IllegalStateException();
        }

        if (customer.getCart().getPrice().intValue() > customer.getBalance().intValue())
            throw new LowBalanceException("You don't have enough money, your balance is only " + customer.getBalance() + " EUR. We do not empty your cart, please remove some of the items.");


        Order order =  new Order();

        order.setOrderId((long) orderRepository.findAll().size()+1);
        order.setCustomer(customer);
        order.setPrice(cart.getPrice());
        order.setOrderItems(customer.getCart().getOrderItems());
        order.setTimestampCreated(LocalDateTime.now());

        saveCustomer(customer, order);

        orderItemRepository.saveAll(order.getOrderItems());
        orderRepository.save(order);
        customerRepository.save(customer);

        return order;
    }

    private void saveCustomer(Customer customer, Order order) {
        List<Order> orders = customer.getOrders();
        if(orders == null) {
            orders = new ArrayList<>();
        }
        orders.add(order);
        customer.setOrders((ArrayList<Order>) orders);
    }
}
