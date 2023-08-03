package com.automobilefleet.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "car_entity")
@Entity
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter @Setter
@EqualsAndHashCode
public class Car implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "_id", nullable = false)
    @Setter(AccessLevel.NONE)
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

    @OneToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "_id", nullable = false)
    private Brand brand;

    @OneToOne
    @JoinColumn(name = "category_id", referencedColumnName = "_id", nullable = false)
    private Category category;

    @Column(name = "car_color", nullable = false)
    private String color;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "created_at", nullable = false)
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}