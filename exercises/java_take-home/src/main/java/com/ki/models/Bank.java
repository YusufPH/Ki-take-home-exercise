package com.ki.models;

public class Bank extends PaymentMethod {
    public Bank(int id) {
        super(id);
        this.setStatus("processed"); // only created if processed by partner
    }
}
