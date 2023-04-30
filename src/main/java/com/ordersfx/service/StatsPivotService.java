package com.ordersfx.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StatsPivotService {

    private Set<PurchaseOrder> currentStatsList;

    public StatsPivotService() {
    }

    public Map<String, Set<PurchaseOrder>> getStatisticsBySquare(Set<PurchaseOrder> currentList) {
        currentStatsList = currentList;
        Map<String, Set<PurchaseOrder>> squarePurchaseOrder = new HashMap<>();
        currentList.forEach(purchaseOrder -> {
            if (squarePurchaseOrder.containsKey(purchaseOrder.getOrganisation())) {
                squarePurchaseOrder.get(purchaseOrder.getOrganisation()).add(purchaseOrder);
            }
             else {
                Set<PurchaseOrder> purchaseOrders = new HashSet<>();
                purchaseOrders.add(purchaseOrder);
                squarePurchaseOrder.put(purchaseOrder.getOrganisation(), purchaseOrders);
            }
        });
        return squarePurchaseOrder;
    }

    public Map<String, Integer> getStatisticsBySquareAndComplete() {
        Map<String, Integer> squareComplete = new HashMap<>();
        getStatisticsBySquare(currentStatsList).forEach((square, purchaseOrder) -> {
            int count = 0;
            for (PurchaseOrder po : purchaseOrder) {
                if (isComplete(po)) {
                    count++;
                }
            }
            squareComplete.put(square, count);
        });
        return squareComplete;
    }

    public Map<String, Integer> getStatisticsBySquareAndGoing() {
        Map<String, Integer> squareGoing = new HashMap<>();
        getStatisticsBySquare(currentStatsList).forEach((square, purchaseOrder) -> {
            int count = 0;
            for (PurchaseOrder po : purchaseOrder) {
                if (!isComplete(po) && isCanceled(po)) {
                    count++;
                }
            }
            squareGoing.put(square, count);
        });
        return squareGoing;
    }

    public Map<String, Integer> getStatisticsInWork() {
        Map<String, Integer> squareInWork = new HashMap<>();
        getStatisticsBySquare(currentStatsList).forEach((square, purchaseOrder) -> {
            int count = 0;
            for (PurchaseOrder po : purchaseOrder) {
                if (po.getOrder().equals("")
                        && !isComplete(po) && isCanceled(po)) {
                    count++;
                }
            }
            squareInWork.put(square, count);
        });
        return squareInWork;
    }

    public Map<String, Integer> getStatisticsByOrder() {
        Map<String, Integer> squareOrders = new HashMap<>();
        getStatisticsBySquare(currentStatsList).forEach((square, purchaseOrder) -> {
            int count = 0;
            for (PurchaseOrder po : purchaseOrder) {
                if (po.getOrder().contains("Заказ")) {
                    count++;
                }
            }
            squareOrders.put(square, count);
        });
        return squareOrders;
    }

    public boolean isComplete(PurchaseOrder purchaseOrder) {
        return purchaseOrder.getComment().toLowerCase().contains("выполн") || purchaseOrder.getPty().contains("Поступление");
    }

    public boolean isCanceled(PurchaseOrder purchaseOrder) {
        return !purchaseOrder.getComment().toLowerCase().contains("отмен");
    }

}
