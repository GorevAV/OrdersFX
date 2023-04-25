package com.ordersfx.service;


public class Stats {

    private String manager;
    private Integer purchaseOrders;

    private Integer orders;

    private Integer notPosted;

    public Stats(String manager, Integer purchaseOrders, Integer orders, Integer notPosted) {
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

    public Integer getPurchaseOrders() {
        return purchaseOrders;
    }

    public void setPurchaseOrders(Integer purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    public Integer getNotPosted() {
        return notPosted;
    }

    public void setNotPosted(Integer notPosted) {
        this.notPosted = notPosted;
    }
}
