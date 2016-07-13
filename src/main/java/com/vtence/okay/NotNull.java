package com.vtence.okay;

import java.io.Serializable;

public class NotNull<T> implements Serializable, Constraint<T> {

    private static final String MISSING = "missing";

    private T value;

    public NotNull(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }

    public void check(Path path, Report report) {
        if (!satisfied()) report.violation(path, MISSING, value);
    }

    private boolean satisfied() {
        return value != null;
    }
}
