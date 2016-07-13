package com.vtence.okay.example.model;

import com.vtence.okay.NotNull;
import com.vtence.okay.Validates;

public class Address {
    private final NotNull<String> firstName;
    private final NotNull<String> lastName;

    public Address(String firstName, String lastName) {
        this.firstName = Validates.notNull(firstName);
        this.lastName = Validates.notNull(lastName);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public String getLastName() {
        return lastName.get();
    }
}
