package com.automobilefleet.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.AUTO;
import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;

@Table(name = "car_image_entity")
@Entity
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
@Builder
@Getter
@Setter
public class CarImage implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "_id", nullable = false)
    @Setter(AccessLevel.NONE)
    private UUID id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "car_id")
    private Car car;

    @Column(name = "image")
    private String linkImage;

    @Column(name = "created_at", nullable = false)
    @Setter(NONE)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarImage carImage = (CarImage) o;
        return Objects.equals(id, carImage.id) &&
                Objects.equals(car, carImage.car) &&
                Objects.equals(linkImage, carImage.linkImage) &&
                Objects.equals(createdAt, carImage.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, car, linkImage, createdAt);
    }
}
