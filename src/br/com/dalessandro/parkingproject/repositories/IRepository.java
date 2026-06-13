package br.com.dalessandro.parkingproject.repositories;

import java.util.List;

public interface IRepository<T> {
    public List<T> getAll();
    public T getById(String key);
    public void save(T entity);
    public void delete(String key);
}
