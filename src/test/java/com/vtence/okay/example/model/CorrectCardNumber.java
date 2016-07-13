package com.vtence.okay.example.model;

import com.vtence.okay.Constraint;
import com.vtence.okay.Path;
import com.vtence.okay.Report;

import java.io.Serializable;
import java.util.regex.Pattern;

public class CorrectCardNumber implements Constraint<String>, Serializable {

    private static final String INCORRECT = "incorrect";

    private static final Pattern VISA_PATTERN = Pattern.compile("4\\d{12}(\\d{3})?");
    private static final Pattern MASTERCARD_PATTERN = Pattern.compile("5[1-5]\\d{14}");
    private static final Pattern AMEX_PATTERN = Pattern.compile("3[47]\\d{13}");

    private final CreditCardType cardType;
    private final String cardNumber;

    public static CorrectCardNumber correctCardNumber(CreditCardType cardType, String cardNumber) {
        return new CorrectCardNumber(cardType, cardNumber);
    }

    public CorrectCardNumber(CreditCardType cardType, String cardNumber) {
        this.cardType = cardType;
        this.cardNumber = cardNumber;
    }

    public String get() {
        return cardNumber;
    }

    public void check(Path path, Report report) {
        if (!satisfied()) report.violation(path, INCORRECT, cardNumber);
    }

    private boolean satisfied() {
        if (cardNumber == null) return false;
        if (cardType.equals(CreditCardType.visa)) return VISA_PATTERN.matcher(cardNumber).matches();
        if (cardType.equals(CreditCardType.mastercard)) return MASTERCARD_PATTERN.matcher(cardNumber).matches();
        return AMEX_PATTERN.matcher(cardNumber).matches();
    }
}
