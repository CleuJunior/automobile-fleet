package com.automobilefleet.entities;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Table(name = "car_specification")
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter @Setter
@EqualsAndHashCode
public class CarSpecification implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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