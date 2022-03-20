package com.samsung.database;

import java.util.Set;

public abstract class PersistentDAO<T> {
    abstract int add(T t);
    abstract Set<T> search(T t);
    abstract Set<T> delete(T t);
    abstract Set<T> modify(T t1, T t2);
}
