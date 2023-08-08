package com.automobilefleet.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Table(name = "car_specification")
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter @Setter
@EqualsAndHashCode
public class CarSpecification implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "_id", nullable = false)
    @Setter(AccessLevel.NONE)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "_id", nullable = false)
    @NonNull
    private Car car;

    @ManyToOne
    @JoinColumn(name = "specification_id", referencedColumnName = "_id", nullable = false)
    @NonNull
    private Specification specification;
}