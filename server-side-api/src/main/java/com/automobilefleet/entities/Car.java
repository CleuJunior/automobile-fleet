package com.automobilefleet.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
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

@Table(name = "car_entity")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Car implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id", nullable = false)
    @Getter
    private Long id;

    @Column(name = "car_name", unique = true, nullable = false)
    @Getter
    @Setter
    private String name;

    @Column(name = "car_description", length = 30, nullable = false)
    @Getter
    @Setter
    private String description;

    @Column(name = "daily_rate", nullable = false)
    @Getter
    @Setter
    private Double dailyRate;

    @Column(name = "car_available", nullable = false)
    @Getter
    @Setter
    private Boolean available;

    @Column(name = "license_plate", length = 30, nullable = false)
    @Getter
    @Setter
    private String licensePlate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id", referencedColumnName = "_id", nullable = false)
    @Getter
    @Setter
    private Brand brand;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "_id", nullable = false)
    @Getter
    @Setter
    private Category category;

    @Column(name = "car_color", nullable = false)
    @Getter
    @Setter
    private String color;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "created_at", nullable = false)
    @Getter
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}