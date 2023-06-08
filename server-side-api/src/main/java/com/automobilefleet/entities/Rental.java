package com.automobilefleet.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "rental_entity")
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
public class Rental implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id", nullable = false)
    @Getter
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", referencedColumnName = "_id", nullable = false)
    @NonNull
    @Getter
    @Setter
    private Car car;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "costumer_id", referencedColumnName = "_id", nullable = false)
    @NonNull
    @Getter
    @Setter
    private Costumer costumer;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "start_date", nullable = false)
    @NonNull
    @Getter
    @Setter
    private LocalDate startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "end_date", nullable = false)
    @NonNull
    @Getter
    @Setter
    private LocalDate endDate;

    @Column(name = "total", nullable = false)
    @NonNull
    @Getter
    @Setter
    private Double total;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "created_at", nullable = false)
    @Getter
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "update_at", nullable = false)
    @Getter
    @Setter
    private LocalDateTime updateAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

    @PostPersist
    public void postPersist() {
        this.createdAt = LocalDateTime.now();
    }
}