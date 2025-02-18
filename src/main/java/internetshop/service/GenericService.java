package internetshop.service;

import java.util.List;

import internetshop.exceptions.DataProcessingException;

public interface GenericService<T, I> {
    T create(T entity) throws DataProcessingException;

    T get(I entityId) throws DataProcessingException;

    T update(T entity) throws DataProcessingException;

    boolean deleteById(I entityId) throws DataProcessingException;

    boolean delete(T entity) throws DataProcessingException;

    List<T> getAll() throws DataProcessingException;
}