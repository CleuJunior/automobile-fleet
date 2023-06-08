package com.automobilefleet.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "costumer_entity")
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
public class Costumer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id", nullable = false)
    @Getter
    private Long id;

    @Column(name = "name", nullable = false)
    @NonNull
    @Getter
    @Setter
    private String name;

    @Column(name = "birth_date", nullable = false)
    @NonNull
    @Getter
    @Setter
    private LocalDate birthDate;

    @Column(name = "email", unique = true, nullable = false)
    @NonNull
    @Getter
    @Setter
    private String email;

    @Column(name = "driver_license", unique = true, nullable = false)
    @NonNull
    @Getter
    @Setter
    private String driverLicense;

    @Column(name = "address")
    @NonNull
    @Getter
    @Setter
    private String address;

    @Column(name = "phone_number", nullable = false)
    @NonNull
    @Getter
    @Setter
    private String phone;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "created_at")
    @Getter
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "update_at", nullable = false)
    @Getter
    private LocalDateTime updateAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

    @PostPersist
    public void postPersist() {
        this.updateAt = LocalDateTime.now();
    }
}