package com.ordersfx.service;

import java.util.Objects;

public class PurchaseOrder {

    private String date;
    private String numberApplication;
    private String organisation;
    private String square;
    private String customer;
    private String itemName;
    private String kr;
    private String count;
    private String unit;
    private String provider;
    private String manager;
    private String datePP;
    private String pty;
    private String datePty;
    private String order;
    private String category;
    private String comment;

    public PurchaseOrder(String date, String numberApplication, String organisation,
                         String square, String customer, String itemName, String kr,
                         String count, String unit, String provider,
                         String manager, String datePP, String pty, String datePty,
                         String order, String category, String comment) {
        this.date = date;
        this.numberApplication = numberApplication;
        this.organisation = organisation;
        this.square = square;
        this.customer = customer;
        this.itemName = itemName;
        this.kr = kr;
        this.count = count;
        this.unit = unit;
        this.provider = provider;
        this.manager = manager;
        this.datePP = datePP;
        this.pty = pty;
        this.datePty = datePty;
        this.order = order;
        this.category = category;
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseOrder that = (PurchaseOrder) o;
        return Objects.equals(date, that.date)
                && Objects.equals(numberApplication, that.numberApplication)
                && Objects.equals(square, that.square)
                && Objects.equals(customer, that.customer)
                && Objects.equals(itemName, that.itemName)
                && Objects.equals(count, that.count)
                && Objects.equals(category, that.category)
                && Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, numberApplication, square, customer, itemName, count, category, comment);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNumberApplication() {
        return numberApplication;
    }

    public void setNumberApplication(String numberApplication) {
        this.numberApplication = numberApplication;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getSquare() {
        return square;
    }

    public void setSquare(String square) {
        this.square = square;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getKr() {
        return kr;
    }

    public void setKr(String kr) {
        this.kr = kr;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getDatePP() {
        return datePP;
    }

    public void setDatePP(String datePP) {
        this.datePP = datePP;
    }

    public String getPty() {
        return pty;
    }

    public void setPty(String pty) {
        this.pty = pty;
    }

    public String getDatePty() {
        return datePty;
    }

    public void setDatePty(String datePty) {
        this.datePty = datePty;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
