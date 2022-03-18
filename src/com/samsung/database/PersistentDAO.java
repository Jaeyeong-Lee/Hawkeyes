package com.samsung.database;

import com.samsung.database.table.EmployeeTable;
import com.samsung.option.CommandOption;

import java.util.List;
import java.util.Set;

public abstract class PersistentDAO<T> {

    abstract int add(T t);
    abstract Set<T> search(T t);
    abstract Set<T> delete(T t);
    abstract Set<T> modify(T t1, T t2);
}
