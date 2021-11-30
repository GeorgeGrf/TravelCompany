package com.travelcompany.eshop.crud;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    T create(T entity);
    Optional<T> read(int id);
//    List<T> read();
    T update(int Id, T e);
    boolean delete(int id);
}
