package com.epam.training.fooddelivery.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Customer extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private BigDecimal balance;
    @Transient
    private List<Order> orders;
    @Transient
    private Cart cart;

    public Customer(String email, String password, String name, BigDecimal balance) {
        super(email, password);
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.orders = new ArrayList<>();
        this.cart = new Cart();
    }
    public Customer(String email, String password, long id, String name, BigDecimal balance) {
        super(email, password);
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.orders = new ArrayList<>();
        this.cart = new Cart();
    }

    public Customer() {
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", orders=" + orders +
                ", cart=" + cart +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id &&
                Objects.equals(name, customer.name) &&
                Objects.equals(balance, customer.balance) &&
                Objects.equals(orders, customer.orders) &&
                Objects.equals(cart, customer.cart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, balance, orders, cart);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Cart getCart() {
        return cart;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
