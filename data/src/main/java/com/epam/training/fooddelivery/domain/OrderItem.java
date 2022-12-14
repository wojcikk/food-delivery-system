package com.epam.training.fooddelivery.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity(name = "ORDER_ITEM")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private int pieces;
    private BigDecimal price;

    public OrderItem(int pieces, BigDecimal price, Food food) {
        this.pieces = pieces;
        this.price = price;
        this.food = food;
    }

    public OrderItem() {
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "pieces=" + pieces +
                ", price=" + price +
                ", food=" + food +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return pieces == orderItem.pieces &&
                Objects.equals(price, orderItem.price) &&
                Objects.equals(food, orderItem.food);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieces, price, food);
    }

    public int getPieces() {
        return pieces;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Food getFood() {
        return food;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
