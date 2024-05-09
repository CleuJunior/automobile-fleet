package com.automobilefleet.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.GenerationType.AUTO;
import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;

@Table(name = "car_specification")
@Entity
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
@Builder
@Getter
@Setter
public class CarSpecification implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "_id", nullable = false)
    @Setter(NONE)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "_id", nullable = false)
    private Car car;

    @ManyToOne
    @JoinColumn(name = "specification_id", referencedColumnName = "_id", nullable = false)
    private Specification specification;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var that = (CarSpecification) o;
        return Objects.equals(id, that.id) &&
                car.equals(that.car) &&
                specification.equals(that.specification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, car, specification);
    }
}