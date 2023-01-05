package com.ki.models;

public class PaymentMethodFactory {
    /**
     * Creates concrete Payment Methods based on the source
     * @param source the payment source
     * @param paymentMethodId ID of this payment
     * @return a new Payment Method
     */
    public static PaymentMethod getPaymentMethod(String source, int paymentMethodId) {
        switch (source) {
            case "bank":
                return new Bank(paymentMethodId);
            case "card":
                return new Card(paymentMethodId);
            default:
                return null;
        }
    }
}
