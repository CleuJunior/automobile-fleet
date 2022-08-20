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

@Table(name = "category_entity")
@Entity
@AllArgsConstructor
@EqualsAndHashCode
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id", nullable = false)
    @Getter
    private Long id;

    @Column(name = "category_name", unique = true, nullable = false)
    @Getter
    @Setter
    private String name;

    @Column(name = "category_description", length = 30, nullable = false)
    @Getter
    @Setter
    private String description;

    @Column(name = "created_at", nullable = false)
    @Getter
    private LocalDateTime createdAt;

    public Category() {
        this.createdAt = LocalDateTime.now();
    }
}