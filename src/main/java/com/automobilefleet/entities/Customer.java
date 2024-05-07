package com.automobilefleet.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.GenerationType.AUTO;
import static java.time.LocalDateTime.now;

@Table(name = "customer_entity")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Customer implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "_id", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "driver_license", unique = true, nullable = false)
    private String driverLicense;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number", nullable = false)
    private String phone;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updatedAt", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = now();
        this.updatedAt = now();
    }

    @PostPersist
    public void postPersist() {
        this.updatedAt = now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer customer)) return false;
        return Objects.equals(getId(), customer.getId()) &&
                Objects.equals(getName(), customer.getName()) &&
                Objects.equals(getBirthdate(), customer.getBirthdate()) &&
                Objects.equals(getEmail(), customer.getEmail()) &&
                Objects.equals(getDriverLicense(), customer.getDriverLicense()) &&
                Objects.equals(getAddress(), customer.getAddress()) &&
                Objects.equals(getPhone(), customer.getPhone()) &&
                Objects.equals(getCreatedAt(), customer.getCreatedAt()) &&
                Objects.equals(getUpdatedAt(), customer.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getBirthdate(), getEmail(), getDriverLicense(), getAddress(), getPhone(), getCreatedAt(), getUpdatedAt());
    }
}