package com.automobilefleet.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

@Table(name = "category_entity")
@Entity
@NoArgsConstructor
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
    @Column(name = "category_description", columnDefinition="TEXT", nullable = false)
    @Getter
    @Setter
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "created_at", nullable = false)
    @Getter
    private LocalDateTime createdAt;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}