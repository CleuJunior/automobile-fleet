package com.automobilefleet.dao;

import java.util.List;
import java.util.Optional;

public interface DAO<T>{
    List<T> findAll();
    T createObject(T t);
    Optional<T> getById(Long id);
    void deleteObject(Long id);
}
