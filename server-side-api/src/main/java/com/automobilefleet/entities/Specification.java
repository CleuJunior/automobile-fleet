package com.automobilefleet.entities;

import lombok.AccessLevel;
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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "specification_entity")
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter @Setter
@EqualsAndHashCode
public class Specification implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "_id", nullable = false)
    @NonNull
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(name = "specification_name", unique = true, nullable = false)
    @NonNull
    private String name;

    @Column(name = "specification_description", columnDefinition="TEXT", nullable = false)
    @NonNull
    private String description;

    @Column(name = "created_at", nullable = false)
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}