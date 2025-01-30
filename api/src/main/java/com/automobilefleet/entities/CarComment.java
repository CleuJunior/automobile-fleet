package com.automobilefleet.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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
import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.GenerationType.AUTO;
import static java.time.LocalDateTime.now;
import static lombok.AccessLevel.NONE;

@Table(name = "car_comment")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class CarComment implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "_id", nullable = false)
    @Setter(NONE)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne(fetch = EAGER, cascade = REMOVE, orphanRemoval = true, targetEntity = Car.class)
    @JoinColumn(name = "car_id", referencedColumnName = "_id", nullable = false)
    private Car car;

    @Column(name = "comment", columnDefinition = "TEXT", nullable = false)
    private String comment;

    @Column(name = "rating", nullable = false)
    private int rating = 0;

    @Column(name = "created_at", nullable = false)
    @Setter(NONE)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        createdAt = now();
        updatedAt = now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        var that = (CarComment) o;

        return rating == that.rating &&
                Objects.equals(id, that.id) &&
                Objects.equals(car, that.car) &&
                Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, car, comment, rating);
    }
}
