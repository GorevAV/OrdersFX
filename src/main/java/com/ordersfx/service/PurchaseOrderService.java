package com.ordersfx.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Comparator;
import java.util.stream.Collectors;

public class PurchaseOrderService {

    private Set<PurchaseOrder> purchaseOrderSet;
    private Set<PurchaseOrder> currentList = new HashSet<>();

    public PurchaseOrderService(Collection<PurchaseOrder> purchaseOrder) {
        this.purchaseOrderSet = new HashSet<>(purchaseOrder.stream().filter(this::isCanceled).toList());
    }

    public PurchaseOrderService() {
    }

    public Map<String, Set<PurchaseOrder>> getStatisticsByManager() {
        Map<String, Set<PurchaseOrder>> managersPurchaseOrder = new HashMap<>();
        currentList.forEach(purchaseOrder -> {
            if (managersPurchaseOrder.containsKey(purchaseOrder.getManager())) {
                managersPurchaseOrder.get(purchaseOrder.getManager()).add(purchaseOrder);
            } else {
                Set<PurchaseOrder> purchaseOrders = new HashSet<>();
                purchaseOrders.add(purchaseOrder);
                managersPurchaseOrder.put(purchaseOrder.getManager(), purchaseOrders);
            }
        });
        return managersPurchaseOrder;
    }
    public Map<String, Integer> getStatisticsByOrder() {
        Map<String, Integer> managersOrders = new HashMap<>();
        getStatisticsByManager().forEach((manager, purchaseOrder) -> {
            int count = 0;
            for (PurchaseOrder po : purchaseOrder) {
                if (po.getOrder().contains("Заказ")) {
                    count++;
                }
            }
            managersOrders.put(manager, count);
        });
        return managersOrders;
    }

    public Map<String, Integer> getStatisticsByNotPosted(){
        Map<String, Integer> managersNotPosted = new HashMap<>();
        getStatisticsByManager().forEach((manager, purchaseOrder) -> {
            int count = 0;
            for (PurchaseOrder po : purchaseOrder) {
                if (po.getOrder().equals("")
                        && !isComplete(po) && isCanceled(po)) {
                    count++;
                }
            }
            managersNotPosted.put(manager, count);
        });
        return managersNotPosted;
    }

    public boolean isComplete(PurchaseOrder purchaseOrder) {
        return purchaseOrder.getComment().toLowerCase().contains("выполн") || purchaseOrder.getPty().contains("Поступление");
    }

    public boolean isCanceled(PurchaseOrder purchaseOrder) {
        return !purchaseOrder.getComment().toLowerCase().contains("отмен");
    }

    public boolean isLateDate(PurchaseOrder purchaseOrder) throws ParseException {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date dateNow = new Date();
        Date datePP = format.parse(purchaseOrder.getDatePP());
        if (dateNow.equals(datePP)) {
            return true;
        }
        return dateNow.after(datePP);
    }

    public ObservableList<PurchaseOrder> getPurchaseOrderObsSet() {

        currentList = purchaseOrderSet;
        return FXCollections.observableArrayList(currentList);
    }

    public ObservableList<PurchaseOrder> search(String newValue) {
        return currentList.stream()
                .filter(purchaseOrder -> purchaseOrder.getManager().toLowerCase().contains(newValue.toLowerCase())
                        || purchaseOrder.getNumberApplication().toLowerCase().contains(newValue.toLowerCase())
                        || purchaseOrder.getOrganisation().toLowerCase().contains(newValue.toLowerCase())
                        || purchaseOrder.getSquare().toLowerCase().contains(newValue.toLowerCase())
                        || purchaseOrder.getOrder().toLowerCase().contains(newValue.toLowerCase())
                        || purchaseOrder.getProvider().toLowerCase().contains(newValue.toLowerCase())
                        || purchaseOrder.getUnit().toLowerCase().contains(newValue.toLowerCase())
                        || purchaseOrder.getDate().toLowerCase().contains(newValue.toLowerCase())
                        || purchaseOrder.getCategory().toLowerCase().contains(newValue.toLowerCase())
                        || purchaseOrder.getItemName().toLowerCase().contains(newValue.toLowerCase())
                        || purchaseOrder.getComment().toLowerCase().contains(newValue.toLowerCase())
                        || purchaseOrder.getPty().toLowerCase().contains(newValue.toLowerCase())
                        || purchaseOrder.getDatePP().toLowerCase().contains(newValue.toLowerCase())
                        || purchaseOrder.getCustomer().toLowerCase().contains(newValue.toLowerCase()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    public ObservableList<PurchaseOrder> getNotPosted() {
        currentList = currentList.stream()
                .filter(purchaseOrder -> purchaseOrder.getOrder().equals("")
                        && !isComplete(purchaseOrder) && isCanceled(purchaseOrder))
                .collect(Collectors.toCollection(HashSet::new));
        return FXCollections.observableArrayList(currentList);
    }


    public ObservableList<PurchaseOrder> getLate() {
        currentList = currentList.stream()
                .filter(purchaseOrder -> {
                    try {
                        return !purchaseOrder.getDatePP().equals("")
                                && isLateDate(purchaseOrder)
                                && !isComplete(purchaseOrder)
                                && isCanceled(purchaseOrder);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toCollection(HashSet::new));
        return FXCollections.observableArrayList(currentList);
    }

    public ObservableList<PurchaseOrder> getAzTwentyOne() {
        currentList = purchaseOrderSet.stream()
                .filter(purchaseOrder -> purchaseOrder.getComment().startsWith("АЗ 2021"))
                .collect(Collectors.toCollection(HashSet::new));

        return currentList.stream()
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    public ObservableList<PurchaseOrder> getAzTwentyTwo() {
        currentList = purchaseOrderSet.stream()
                .filter(purchaseOrder -> purchaseOrder.getComment().startsWith("АЗ 2022"))
                .collect(Collectors.toCollection(HashSet::new));

        return currentList.stream()
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    public ObservableList<PurchaseOrder> getAzTwentyThree() {
        currentList = purchaseOrderSet.stream()
                .filter(purchaseOrder -> purchaseOrder.getComment().startsWith("АЗ 2023"))
                .collect(Collectors.toCollection(HashSet::new));

        return currentList.stream()
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    public ObservableList<PurchaseOrder> getKr() {
        currentList = purchaseOrderSet.stream()
                .filter(purchaseOrder -> purchaseOrder.getKr().contains("Да"))
                .collect(Collectors.toCollection(HashSet::new));

        return currentList.stream()
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    public ObservableList<PurchaseOrder> getTwentyThree() {
        currentList = purchaseOrderSet.stream()
                .filter(purchaseOrder -> purchaseOrder.getComment().startsWith("2023"))
                .collect(Collectors.toCollection(HashSet::new));

        return currentList.stream()
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    public ObservableList<PurchaseOrder> getCurrentObsList() {
        return FXCollections.observableArrayList(currentList);
    }

    public Set<PurchaseOrder> getCurrentList() {
        return currentList.stream().sorted(Comparator.comparing(PurchaseOrder::getNumberApplication))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public Set<PurchaseOrder> getPurchaseOrderSet() {
        return purchaseOrderSet;
    }

    public void setCurrentList(Set<PurchaseOrder> currentList) {
        this.currentList = currentList;
    }

    public void setPurchaseOrderSet(Set<PurchaseOrder> purchaseOrderSet) {
        this.purchaseOrderSet = purchaseOrderSet;
    }

}
