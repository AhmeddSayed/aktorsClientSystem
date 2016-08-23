package com.kreative.aktorsclientsystem.models;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Date;

/**
 *
 * @author Ahmed
 */
public class Order {

    // Order parameters
    private final int orderId;
    private final int productId;
    private final int clientId;
    private Date transactionDate;
    private int originalPrice;
    private int convertedPrice;

    // order constructor
    public Order(int orderId, int productId, int clientId) {
        this.clientId = clientId;
        this.orderId = orderId;
        this.productId = productId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(int originalPrice) {
        this.originalPrice = originalPrice;
    }

    public int getConvertedPrice() {
        return convertedPrice;
    }

    public void setConvertedPrice(int convertedPrice) {
        this.convertedPrice = convertedPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getProductId() {
        return productId;
    }

    public int getClientId() {
        return clientId;
    }
}
