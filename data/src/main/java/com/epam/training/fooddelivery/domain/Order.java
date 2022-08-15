package com.epam.training.fooddelivery.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    @Column
    private BigDecimal price;
    @Column
    @CreationTimestamp
    private LocalDateTime timestampCreated;
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    public Order(Long orderId, long customerId, LocalDateTime timestampCreated) {
        this.id = orderId;
        this.customer.setId(customerId);
        this.price = new BigDecimal(0);
        this.timestampCreated = timestampCreated;
        this.orderItems = new ArrayList<>();
    }

    public Order(Long orderId, long customerId, BigDecimal price, LocalDateTime timestampCreated, List<OrderItem> orderItems) {
        this.id = orderId;
        this.customer.setId(customerId);
        this.price = price;
        this.timestampCreated = timestampCreated;
        this.orderItems = orderItems;
    }

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + id +
                ", customerId=" + customer.getId() +
                ", price=" + price +
                ", timestampCreated=" + timestampCreated +
                ", orderItems=" + orderItems +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return customer.getId() == order.customer.getId() &&
                Objects.equals(id, order.id) &&
                Objects.equals(price, order.price) &&
                Objects.equals(timestampCreated, order.timestampCreated) &&
                Objects.equals(orderItems, order.orderItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer.getId(), price, timestampCreated, orderItems);
    }

    public Long getOrderId() {
        return id;
    }

    public long getCustomerId() {
        return customer.getId();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDateTime getTimestampCreated() {
        return timestampCreated;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void setOrderItems(Long orderId) {
        this.id = orderId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTimestampCreated(LocalDateTime timestampCreated) {
        this.timestampCreated = timestampCreated;
    }

    public void setOrderId(Long orderId) {
        this.id = orderId;
    }

    public String getTimestampCreatedFormat() {
        return timestampCreated.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

    public String printOrderedItems() {
        StringBuilder string = new StringBuilder();
        for(OrderItem orderItem : this.getOrderItems()) {
            string.append(orderItem.getFood().getName());
            string.append(", ");
        }
        return string.substring(0, string.length() - 2);
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
