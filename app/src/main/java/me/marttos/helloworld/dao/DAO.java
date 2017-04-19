package me.marttos.helloworld.dao;

import android.database.Cursor;

public interface DAO<E>
{
    boolean insert(E model);
    void update(E model);
    void delete(E model);
    E getModel(Long id);
    E toModel(Cursor c);
}
