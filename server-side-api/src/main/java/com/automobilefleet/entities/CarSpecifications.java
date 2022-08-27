package com.automobilefleet.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Table(name = "car_specification")
@Entity
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class CarSpecifications implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id", nullable = false)
    @Getter
    private Long id;

    @OneToOne
    @JoinColumn(name = "car_id", referencedColumnName = "_id", nullable = false)
    @Getter
    @Setter
    private Car car;

    @OneToOne
    @JoinColumn(name = "specification_id", referencedColumnName = "_id", nullable = false)
    @Getter
    @Setter
    private Specification specification;

}