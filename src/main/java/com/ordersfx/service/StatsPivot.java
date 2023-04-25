package com.ordersfx.service;


public class StatsPivot {

    private String square;
    private Integer purchaseOrders;

    private Integer completedOrders;
    private Integer orders;

    private Integer going;

    private Integer inWork;

    public StatsPivot(String square, Integer purchaseOrders, Integer completedOrders, Integer orders, Integer going, Integer inWork) {
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

    public Integer getPurchaseOrders() {
        return purchaseOrders;
    }

    public void setPurchaseOrders(Integer purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }

    public Integer getCompletedOrders() {
        return completedOrders;
    }

    public void setCompletedOrders(Integer completedOrders) {
        this.completedOrders = completedOrders;
    }

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    public Integer getGoing() {
        return going;
    }

    public void setGoing(Integer going) {
        this.going = going;
    }

    public Integer getInWork() {
        return inWork;
    }

    public void setInWork(Integer inWork) {
        this.inWork = inWork;
    }
}
