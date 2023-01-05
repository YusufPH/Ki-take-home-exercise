package com.ki.models;

/**
 * This abstract class will make it easier to create new PaymentMethods down the road.
 */
public abstract class PaymentMethod {
    private String status;
    private int id;

    public PaymentMethod(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
