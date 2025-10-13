package com.nicoletti.wineapp.dao;

import java.sql.SQLException;
import java.util.List;

public interface HelperDAO<T> {

    void save(T t) throws SQLException;

    void update(T t) throws SQLException;

    void deleteById(long id) throws SQLException;

    T findById(long id) throws SQLException;

    List<T> findAll() throws SQLException;

}
