package com.raven.DAO;

import java.util.List;

public interface InterfaceDAO<T> {
    public List<T> selectAll();
    public boolean insert(T t);
    public boolean update(T t);
    public boolean delete(T t);
    public T selectById(T t);
}
