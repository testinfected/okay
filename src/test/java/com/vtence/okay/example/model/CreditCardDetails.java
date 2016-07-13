package com.vtence.okay.example.model;

import com.vtence.okay.Constraint;
import com.vtence.okay.NotNull;
import com.vtence.okay.Valid;
import com.vtence.okay.Validates;

import static com.vtence.okay.Validates.notEmpty;
import static com.vtence.okay.example.model.CorrectCardNumber.correctCardNumber;

public class CreditCardDetails extends PaymentMethod {

    private final CreditCardType cardType;
    private final Constraint<String> cardNumber;
    private final NotNull<String> cardExpiryDate;
    private final Valid<Address> billingAddress;

    public CreditCardDetails(CreditCardType cardType, String cardNumber, String cardExpiryDate, Address billingAddress) {
        this.cardType = cardType;
        this.cardNumber = Validates.both(notEmpty(cardNumber), correctCardNumber(cardType, cardNumber));
        this.cardExpiryDate = Validates.notNull(cardExpiryDate);
        this.billingAddress = Validates.validityOf(billingAddress);
    }

    public static CreditCardDetails visa(String number, String expiryDate, Address billingAddress) {
        return new CreditCardDetails(CreditCardType.visa, number, expiryDate, billingAddress);
    }

    public CreditCardType getCardType() {
        return cardType;
    }

    public String getCardCommonName() {
        return cardType.commonName();
    }

    public String getCardNumber() {
        return cardNumber.get();
    }

    public String getCardExpiryDate() {
        return cardExpiryDate.get();
    }

    public String getFirstName() {
        return billingAddress.get().getFirstName();
    }

    public String getLastName() {
        return billingAddress.get().getLastName();
    }
}
