package com.automobilefleet.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
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
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "rental_entity")
@Entity
@AllArgsConstructor
@EqualsAndHashCode
public class Rental implements Serializable {
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
    @JoinColumn(name = "costumer_id", referencedColumnName = "_id", nullable = false)
    @Getter
    @Setter
    private Costumer costumer;

    @Column(name = "start_date", nullable = false)
    @Getter
    @Setter
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    @Getter
    @Setter
    private LocalDate endDate;

    @Column(name = "total", nullable = false)
    @Getter
    @Setter
    private Double total;

    @Column(name = "created_at")
    @Getter
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    @Getter
    @Setter
    private LocalDateTime updateAt;

    public Rental() {
        this.createdAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }
}