package com.vtence.okay.example;

import com.vtence.okay.ConstraintViolationException;
import com.vtence.okay.Ensure;
import com.vtence.okay.example.model.Address;
import com.vtence.okay.example.model.CreditCardDetails;
import org.junit.Test;

import static com.vtence.okay.example.model.CreditCardDetails.visa;
import static com.vtence.okay.example.model.CreditCardType.visa;
import static com.vtence.okay.testing.ValidationMatchers.on;
import static com.vtence.okay.testing.ValidationMatchers.succeeds;
import static com.vtence.okay.testing.ValidationMatchers.validationOf;
import static com.vtence.okay.testing.ValidationMatchers.violates;
import static com.vtence.okay.testing.ValidationMatchers.withMessage;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CreditCardDetailsTest {

    String MISSING = null;
    String EMPTY = "";
    String INCORRECT = "1234567890123456";

    @Test
    public void
    areInvalidWithAnEmptyOrIncorrectCardNumber() {
        assertThat("empty number", validationOf(visa(EMPTY, "...", anAddress())),
                   violates(on("cardNumber"), withMessage("empty")));
        assertThat("incorrect number", validationOf(visa(INCORRECT, "...", anAddress())),
                   violates(on("cardNumber"), withMessage("incorrect")));
    }

    @Test
    public void
    areInvalidWithoutACardExpiryDate() {
        assertThat("missing expiry date", validationOf(visa("...", MISSING, anAddress())),
                   violates(on("cardExpiryDate"), withMessage("missing")));
    }

    @Test
    public void
    areInvalidWithAnInvalidAddress() {
        assertThat("missing first name", validationOf(visa("...", "...", new Address(MISSING, "Doe"))),
                   violates(on("billingAddress.firstName"), withMessage("missing")));
        assertThat("missing last name", validationOf(visa("...", "...", new Address("John", MISSING))),
                   violates(on("billingAddress.lastName"), withMessage("missing")));
        assertThat("invalid address", validationOf(visa("...", "...", new Address(MISSING, MISSING))),
                   violates(on("billingAddress"), withMessage("invalid")));
    }

    @Test
    public void
    areValidWithAllRequiredDetails() {
        assertThat("valid card", validationOf(visa("4111111111111111", "12/26", anAddress())), succeeds());
    }

    @Test(expected = ConstraintViolationException.class)
    public void areRejectedWhenInvalid() {
        Ensure.valid(visa("5100000000000019", "12/26", anAddress()));
    }

    @Test
    public void
    knowsItsDetails() {
        CreditCardDetails cardDetails = visa("4111111111111111", "12/26", new Address("John", "Doe"));
        assertThat("card type", cardDetails.getCardType(), equalTo(visa));
        assertThat("card type", cardDetails.getCardCommonName(), equalTo("Visa"));
        assertThat("card number", cardDetails.getCardNumber(), equalTo("4111111111111111"));
        assertThat("card expiry date", cardDetails.getCardExpiryDate(), equalTo("12/26"));
        assertThat("cardholder first name", cardDetails.getFirstName(), equalTo("John"));
        assertThat("cardholder last name", cardDetails.getLastName(), equalTo("Doe"));
    }

    private Address anAddress() {
        return new Address("...", "...");
    }
}
