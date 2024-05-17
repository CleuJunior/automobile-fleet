package com.automobilefleet.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.AUTO;
import static java.time.LocalDateTime.now;
import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;

@Table(name = "car_entity")
@Entity
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
@Builder
@Getter
@Setter
public class Car implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "_id", nullable = false)
    @Setter(NONE)
    private UUID id;

    @Column(name = "car_name", unique = true, nullable = false)
    private String name;

    @Column(name = "car_description", length = 30, nullable = false)
    private String description;

    @Column(name = "daily_rate", nullable = false)
    private Double dailyRate;

    @Column(name = "car_available", nullable = false)
    private boolean available;

    @Column(name = "license_plate", length = 30, nullable = false)
    private String licensePlate;

    @OneToOne(fetch = LAZY, cascade = REMOVE, orphanRemoval = true, targetEntity = Brand.class)
    @JoinColumn(name = "brand_id", referencedColumnName = "_id", nullable = false)
    private Brand brand;

    @OneToOne(fetch = LAZY, cascade = REMOVE, orphanRemoval = true, targetEntity = Category.class)
    @JoinColumn(name = "category_id", referencedColumnName = "_id", nullable = false)
    private Category category;

    @Column(name = "car_color", nullable = false)
    private String color;

    @Column(name = "created_at", nullable = false)
    @Setter(NONE)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var car = (Car) o;
        return available == car.available &&
                Objects.equals(id, car.id) &&
                Objects.equals(name, car.name) &&
                Objects.equals(description, car.description) &&
                Objects.equals(dailyRate, car.dailyRate) &&
                Objects.equals(licensePlate, car.licensePlate) &&
                Objects.equals(brand, car.brand) &&
                Objects.equals(category, car.category) &&
                Objects.equals(color, car.color) &&
                Objects.equals(createdAt, car.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, dailyRate, available, licensePlate, brand, category, color, createdAt);
    }
}