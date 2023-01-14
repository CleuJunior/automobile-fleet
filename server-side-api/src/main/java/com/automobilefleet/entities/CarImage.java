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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Table(name = "brand_entity")
@Entity
@AllArgsConstructor
@EqualsAndHashCode
public class CarImage implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id", nullable = false)
    @Getter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id")
    @Getter
    @Setter
    private String carId;

    @Lob
    @Column(name = "image", columnDefinition = "BLOB")
    @Getter
    @Setter
    private byte[] image;
    @Column(name = "created_at", nullable = false)
    @Getter
    private LocalDateTime createdAt;

    public CarImage() {
        if(createdAt == null)
            this.createdAt = LocalDateTime.now();
    }
}
