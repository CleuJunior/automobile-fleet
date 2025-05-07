package br.com.producer.comment_producer.entities;

public record Car(
        String id,
        String name,
        String licensePlate,
        Brand brand,
        String color
) {
}
