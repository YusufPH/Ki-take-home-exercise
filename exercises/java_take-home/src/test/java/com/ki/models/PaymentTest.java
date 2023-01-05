package com.ki.models;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class PaymentTest {

    @Test
    public void testPaymentFromCsvRow() {

        int CUSTOMER_ID = 123;
        String AMOUNT = "2000";
        String CARD_STATUS = "processed";
        int CARD_ID = 45;
        String DATE = "2019-02-01";

        String data[] = new String[] {
                String.valueOf(CUSTOMER_ID),
                DATE,
                AMOUNT,
                String.valueOf(CARD_ID),
                CARD_STATUS,
        };

        Payment payment = new Payment(data, "card");

        assertEquals(CUSTOMER_ID, payment.getCustomerId());
        assertEquals(1960, payment.getAmount());
        assertEquals(40, payment.getFee());
        assertEquals(LocalDate.of(2019, 2, 1), payment.getDate());

        assertTrue(payment.method instanceof Card);

        PaymentMethod card = payment.method;
        assertEquals(CARD_ID, card.getId());
        assertEquals(CARD_STATUS, card.getStatus());
    }

    @Test
    public void testIsSuccessful() {
        Payment payment = new Payment();
        payment.method = new Card(0);
        payment.method.setStatus("processed");
        assertTrue(payment.isSuccessful());
    }

    @Test
    public void testIsSuccessfulDeclined() {
        Payment payment = new Payment();
        payment.method = new Card(0);
        payment.method.setStatus("declined");
        assertFalse(payment.isSuccessful());
    }

    @Test
    public void testIsSuccessfulErrored() {
        Payment payment = new Payment();
        payment.method = new Card(0);
        payment.method.setStatus("error");
        assertFalse(payment.isSuccessful());
    }

    @Test
    public void testPaymentFromBankCsvRow() {

        int CUSTOMER_ID = 123;
        String AMOUNT = "2000";
        int BANK_ID = 45;
        String DATE = "2022-01-05";

        String data[] = new String[] {
                String.valueOf(CUSTOMER_ID),
                DATE,
                AMOUNT,
                String.valueOf(BANK_ID),
        };

        Payment payment = new Payment(data, "bank");

        assertEquals(CUSTOMER_ID, payment.getCustomerId());
        assertEquals(1960, payment.getAmount());
        assertEquals(40, payment.getFee());
        assertEquals(LocalDate.of(2022, 1, 5), payment.getDate());

        assertTrue(payment.method instanceof Bank);

        PaymentMethod bank = payment.method;
        assertEquals(BANK_ID, bank.getId());
    }

    @Test
    public void testBankIsSuccessful() {
        Payment payment = new Payment();
        payment.method = new Bank(0);
        assertTrue(payment.isSuccessful());
    }
}
