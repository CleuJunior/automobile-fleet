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
import java.time.LocalDateTime;

@Table(name = "specification_entity")
@Entity
@AllArgsConstructor
@EqualsAndHashCode
public class Specification implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id", nullable = false)
    @Getter
    private Long id;

    @Column(name = "specification_name", unique = true, nullable = false)
    @Getter
    @Setter
    private String name;

    @Column(name = "specification_description", columnDefinition="TEXT", nullable = false)
    @Getter
    @Setter
    private String description;

    @Column(name = "created_at", nullable = false)
    @Getter
    private LocalDateTime createdAt;

    public Specification() {
        this.createdAt = LocalDateTime.now();
    }
}