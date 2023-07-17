package com.automobilefleet.dao;

import java.util.List;
import java.util.Optional;

public interface DAO<T>{
    List<T> findAll();
    int save(T t);
    Optional<T> getById(Long id);
    void deleteById(Long id);
}
