package br.com.dalessandro.parkingproject.services;

import java.util.List;

public interface IService<T> {
    public List<T> getAll();
    public T getById(String key);
    public void save(T entity);
    public void delete(String key);
}