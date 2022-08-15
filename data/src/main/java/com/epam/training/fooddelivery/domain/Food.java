package com.epam.training.fooddelivery.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private BigDecimal calorie;
    @Column
    private BigDecimal price;
    @Column
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "CATEGORY")
    private Category category;

    public Food(String name, BigDecimal calorie, String description, BigDecimal price, Category category) {
        this.name = name;
        this.calorie = calorie;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public Food() {
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", calorie=" + calorie +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category=" + category +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return Objects.equals(name, food.name) &&
                Objects.equals(calorie, food.calorie) &&
                Objects.equals(description, food.description) &&
                Objects.equals(price, food.price) &&
                category == food.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, calorie, description, price, category);
    }

    public String getName() {
        return name;
    }

    public BigDecimal getCalorie() {
        return calorie;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }
}
