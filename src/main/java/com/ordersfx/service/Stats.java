package com.ordersfx.service;


public class Stats {

    private String manager;
    private int purchaseOrders;

    private int orders;

    private int notPosted;

    public Stats(String manager, int purchaseOrders, int orders, int notPosted) {
        this.manager = manager;
        this.purchaseOrders = purchaseOrders;
        this.orders = orders;
        this.notPosted = notPosted;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public int getPurchaseOrders() {
        return purchaseOrders;
    }

    public void setPurchaseOrders(int purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    public int getNotPosted() {
        return notPosted;
    }

    public void setNotPosted(int notPosted) {
        this.notPosted = notPosted;
    }
}
