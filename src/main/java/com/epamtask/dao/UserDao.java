package com.epamtask.dao;

import java.util.Map;
import java.util.Optional;

public interface UserDao<T> extends Dao<Long, T> {
    void update(T entity);
    Optional<T> findById(Long id);
    Optional<T> findByUsername(String username);
    void deleteById(Long id);
}