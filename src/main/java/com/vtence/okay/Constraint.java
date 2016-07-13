package com.vtence.okay;

public interface Constraint<T> {

    T get();

    void check(Path path, Report report);
}
