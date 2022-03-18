package com.samsung.database;

import com.samsung.database.table.EmployeeTable;

import java.util.List;

public abstract class PersistentDAO<T> {

    abstract int add(T t);
    abstract List<T> search(T t);
    abstract List<T> delete(T t);
    abstract List<T> modify(T t1, T t2);
}
