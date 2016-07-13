package com.vtence.okay;

public interface Report {

    <T> void violation(Path path, String error, T offendingValue);
}
