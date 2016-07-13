package com.vtence.okay.example;

import com.vtence.okay.example.model.Address;
import org.junit.Test;

import static com.vtence.okay.testing.ValidationMatchers.on;
import static com.vtence.okay.testing.ValidationMatchers.succeeds;
import static com.vtence.okay.testing.ValidationMatchers.validationOf;
import static com.vtence.okay.testing.ValidationMatchers.violates;
import static com.vtence.okay.testing.ValidationMatchers.withMessage;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class AddressTest {

    String MISSING = null;

    @Test public void
    isInvalidWithoutAFirstName() {
        assertThat("missing first name", validationOf(new Address(MISSING, "Doe")), violates(on("firstName"), withMessage("missing")));
    }

    @Test public void
    isInvalidWithoutALastName() {
        assertThat("missing last name", validationOf(new Address("John", MISSING)), violates(on("lastName"), withMessage("missing")));
    }

    @Test public void
    isValidWithAFullName() {
        assertThat("valid address", validationOf(new Address("Joe", "Blow")), succeeds());
    }

    @Test public void
    knowsItsName() {
        assertThat("first name", new Address("John", "Doe").getFirstName(), equalTo("John"));
        assertThat("last name", new Address("John", "Doe").getLastName(), equalTo("Doe"));
    }
}
