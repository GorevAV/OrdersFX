package com.ordersfx.service;


public class StatsPivot {

    private String square;
    private int purchaseOrders;

    private int completedOrders;
    private int orders;

    private int going;

    private int inWork;

    public StatsPivot(String square, int purchaseOrders, int completedOrders, int orders, int going, int inWork) {
        this.square = square;
        this.purchaseOrders = purchaseOrders;
        this.completedOrders = completedOrders;
        this.orders = orders;
        this.going = going;
        this.inWork = inWork;
    }

    public String getSquare() {
        return square;
    }

    public void setSquare(String square) {
        this.square = square;
    }

    public int getPurchaseOrders() {
        return purchaseOrders;
    }

    public void setPurchaseOrders(int purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }

    public int getCompletedOrders() {
        return completedOrders;
    }

    public void setCompletedOrders(int completedOrders) {
        this.completedOrders = completedOrders;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    public int getGoing() {
        return going;
    }

    public void setGoing(int going) {
        this.going = going;
    }

    public int getInWork() {
        return inWork;
    }

    public void setInWork(int inWork) {
        this.inWork = inWork;
    }
}
