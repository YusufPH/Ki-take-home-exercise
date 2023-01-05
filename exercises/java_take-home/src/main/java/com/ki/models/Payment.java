package com.ki.models;

import com.ki.Config;

import java.math.BigDecimal;
import java.time.LocalDate;


public class Payment {

    private int customerId;
    private LocalDate date;
    private int amount;
    private int fee;
    public PaymentMethod method;

    public Payment() {
    }

    public Payment(String[] data, String source) {
        this.setCustomerId(Integer.parseInt(data[0]));

        BigDecimal paymentFeeRate = Config.getPaymentFeeRate();
        int totalAmount = Integer.parseInt(data[2]);
        this.setFee(paymentFeeRate.multiply(new BigDecimal(totalAmount)).intValue());
        this.setAmount(totalAmount - this.getFee());
        this.setDate(LocalDate.parse(data[1]));

        // We'll use a factory to create the PaymentMethod as this class shouldn't necessarily care about the type
        PaymentMethod method = PaymentMethodFactory.getPaymentMethod(source, Integer.parseInt(data[3]));

        if (method instanceof Card) {
            method.setStatus(data[4]);
        }

        this.method = method;
    }

    public boolean isSuccessful() {
        return method.getStatus().equals("processed");
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }
}
