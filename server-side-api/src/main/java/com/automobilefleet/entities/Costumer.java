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
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "costumer_entity")
@Entity
@AllArgsConstructor
@EqualsAndHashCode
public class Costumer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id", nullable = false)
    @Getter
    private Long id;

    @Column(name = "costumer_name", nullable = false)
    @Getter
    @Setter
    private String name;

    @Column(name = "costumer_bd", nullable = false)
    @Getter
    @Setter
    private LocalDate birthDate;

    @Column(name = "costumer_email", unique = true, nullable = false)
    @Getter
    @Setter
    private String email;

    @Column(name = "drive_license", unique = true, nullable = false)
    @Getter
    @Setter
    private String driveLicense;

    @Column(name = "address")
    @Getter
    @Setter
    private String address;

    @Column(name = "costumer_phone_number", nullable = false)
    @Getter
    @Setter
    private String phone;

    @Column(name = "created_at")
    @Getter
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    @Getter
    @Setter
    private LocalDateTime updateAt;

    public Costumer() {
        this.createdAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }
}