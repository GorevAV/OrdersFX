package com.ordersfx;

import com.ordersfx.service.*;
import com.ordersfx.util.ReadExcelFile;
import com.ordersfx.util.WriteExcelFileAll;
import com.ordersfx.util.WriteExcelFileStats;
import com.ordersfx.util.WriteExcelFileStatsPivot;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class StatisticsController {

    @FXML
    private Button allPurchaseOrders;

    @FXML
    private Button azTwentyOneButton;

    @FXML
    private Button azTwentyThreeButton;

    @FXML
    private Button azTwentyTwoButton;

    @FXML
    private TableColumn<PurchaseOrder, String> commentLateColumn;

    @FXML
    private TableColumn<PurchaseOrder, String> commentNotpostedColumn;

    @FXML
    private TableColumn<PurchaseOrder, String> countLateColumn;

    @FXML
    private TableColumn<PurchaseOrder, String> countNotpostedColumn;

    @FXML
    private TableColumn<Stats, Integer> countOrdersStatsColumn;

    @FXML
    private TableColumn<Stats, Integer> countStatsColumn;

    @FXML
    private TableColumn<PurchaseOrder, String> dateLateColumn;

    @FXML
    private TableColumn<PurchaseOrder, String> dateNotpostedColumn;

    @FXML
    private TableColumn<PurchaseOrder, String> itemNameLateColumn;

    @FXML
    private TableColumn<PurchaseOrder, String> itemNameNotpostedColumn;

    @FXML
    private TextField keywoordTextField;

    @FXML
    private Button krButton;

    @FXML
    private Button lateButton;

    @FXML
    private TableColumn<Stats, String> managerStatsColumn;

    @FXML
    private TableColumn<PurchaseOrder, String> managerLateColumn;

    @FXML
    private TableColumn<PurchaseOrder, String> managerNorPostedColumn;

    @FXML
    private Button notPostedButton;

    @FXML
    private TableColumn<Stats, Integer> notPostedStatsColumn;


    @FXML
    private TableColumn<PurchaseOrder, String> purchaseOrderLateColumn;

    @FXML
    private TableColumn<PurchaseOrder, String> purchaseOrderNotpostedColumn;

    @FXML
    private Button readExcel;

    @FXML
    private Button returnButton;

    @FXML
    private TableColumn<PurchaseOrder, String> squareLateColumn;

    @FXML
    private TableColumn<PurchaseOrder, String> squareNotPostedColumn;

    @FXML
    private TableView<Stats> tableStats;

    @FXML
    private TableView<PurchaseOrder> tableNotPosted;

    @FXML
    private TableView<PurchaseOrder> tableLate;

    @FXML
    private Button twentyThreeButton;

    @FXML
    private TableColumn<PurchaseOrder, String> unitLateColumn;

    @FXML
    private TableColumn<PurchaseOrder, String> unitNotpostedColumn;

    @FXML
    private Button writeExcel;

    @FXML
    private TableView<StatsPivot> pivotTable;

    @FXML
    private TableColumn<StatsPivot, String> squarePivotColumn;

    @FXML
    private TableColumn<StatsPivot, Integer> purchaseOrdersPivotColumn;

    @FXML
    private TableColumn<StatsPivot, Integer> ordersPivotColumn;

    @FXML
    private TableColumn<StatsPivot, Integer> completePivotColumn;

    @FXML
    private TableColumn<StatsPivot, Integer> goingPivotColumn;

    @FXML
    private TableColumn<StatsPivot, Integer> inWorkPivotColumn;

    @FXML
    private Button writeExcelPivot;

    @FXML
    private Button writeExcelStats;

    private List<StatsPivot> statPivotList;

    private List<Stats> statList;
    private List<PurchaseOrder> readFile;
    private PurchaseOrderService purchaseOrderService;

    @FXML
    void initialize() {

        returnButton.setOnAction(event -> {
            returnButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Main.fxml"));
            try {
                loader.load();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });

        readExcel.setOnAction(e -> {
            File file = new FileChooser().showOpenDialog(new Stage());
            String path = file.getAbsolutePath();
            try {
                ReadExcelFile readExcelFile = new ReadExcelFile();
                if (readFile != null) {
                    readFile.addAll(readExcelFile.readExcel(path));
                } else {
                    readFile = readExcelFile.readExcel(path);
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            purchaseOrderService = new PurchaseOrderService(readFile);
        });

        writeExcel.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel files (*.xlsx)", "*.xlsx");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showSaveDialog(new Stage());
            try {
                WriteExcelFileAll writeExcelFileAll = new WriteExcelFileAll();
                writeExcelFileAll.writeExcelFile(purchaseOrderService.getCurrentList(), file.getAbsolutePath(), file.getName());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        writeExcelStats.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel files (*.xlsx)", "*.xlsx");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showSaveDialog(new Stage());
            try {
                WriteExcelFileStats writeExcelFileStats = new WriteExcelFileStats();
                writeExcelFileStats.writeExcelFile(statList, file.getAbsolutePath(), file.getName());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        writeExcelPivot.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel files (*.xlsx)", "*.xlsx");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showSaveDialog(new Stage());
            try {
                WriteExcelFileStatsPivot writeExcelFileStatsPivot = new WriteExcelFileStatsPivot();
                writeExcelFileStatsPivot.writeExcelFile(statPivotList, file.getAbsolutePath(), file.getName());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });


        notPostedButton.setOnAction(e -> {
            fillTableNotPosted();
            tableNotPosted.setItems(purchaseOrderService.getNotPosted());
            autoResizeColumns(tableNotPosted);
        });

        lateButton.setOnAction(e -> {
            fillTableLate();
            tableLate.setItems(purchaseOrderService.getLate());
            autoResizeColumns(tableLate);
        });

        allPurchaseOrders.setOnAction(e -> {
            purchaseOrderService.getPurchaseOrderObsSet();
            fillTableStats();
            fillPivotTable();
        });

        azTwentyOneButton.setOnAction(e -> {
            purchaseOrderService.getAzTwentyOne();
            fillTableStats();
            fillPivotTable();
        });

        azTwentyTwoButton.setOnAction(e -> {
            purchaseOrderService.getAzTwentyTwo();
            fillTableStats();
            fillPivotTable();
        });

        azTwentyThreeButton.setOnAction(e -> {
            purchaseOrderService.getAzTwentyThree();
            fillTableStats();
            fillPivotTable();
        });

        krButton.setOnAction(e -> {
            purchaseOrderService.getKr();
            fillTableStats();
            fillPivotTable();
        });

        twentyThreeButton.setOnAction(e -> {
            purchaseOrderService.getTwentyThree();
            fillTableStats();
            fillPivotTable();
        });

        searchBarKeywordTextField(tableLate);
        searchBarKeywordTextField(tableNotPosted);

    }

    private void searchBarKeywordTextField(TableView<PurchaseOrder> table) {
        keywoordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                table.setItems(purchaseOrderService.getCurrentObsList());
            } else {
                table.setItems(purchaseOrderService.search(newValue));
            }
        });
    }

    private void fillTableStats() {
        managerStatsColumn.setCellValueFactory(new PropertyValueFactory<>("manager"));
        countStatsColumn.setCellValueFactory(new PropertyValueFactory<>("purchaseOrders"));
        countOrdersStatsColumn.setCellValueFactory(new PropertyValueFactory<>("orders"));
        notPostedStatsColumn.setCellValueFactory(new PropertyValueFactory<>("notPosted"));

        tableStats.setItems(getStats());
    }

    private ObservableList<Stats> getStats() {
        List<Stats> statsList = new ArrayList<>();
        Map<String, Set<PurchaseOrder>> managersPurchaseOrder;
        managersPurchaseOrder = purchaseOrderService.getStatisticsByManager();
        for (Map.Entry<String, Set<PurchaseOrder>> entry : managersPurchaseOrder.entrySet()) {
            String manager = entry.getKey();
            int purchaseOrdersCount = entry.getValue().size();
            int orders = 0;
            Map<String, Integer> tempStatsOrders = purchaseOrderService.getStatisticsByOrder();
            for (Map.Entry<String, Integer> temp : tempStatsOrders.entrySet()) {
                if (temp.getKey().equals(manager)) {
                    orders = temp.getValue();
                }
            }
            int notPosted = 0;
            Map<String, Integer> tempStatsNotPosted = purchaseOrderService.getStatisticsByNotPosted();
            for (Map.Entry<String, Integer> temp : tempStatsNotPosted.entrySet()) {
                if (temp.getKey().equals(manager)) {
                    notPosted = temp.getValue();
                }
            }
            statsList.add(new Stats(manager, purchaseOrdersCount, orders, notPosted));
        }
        statList = statsList;
        return FXCollections.observableArrayList(statsList);
    }

    private ObservableList<StatsPivot> getPivotStats() {
        StatsPivotService statsPivotService = new StatsPivotService();
        List<StatsPivot> statsPivotList = new ArrayList<>();
        Map<String, Set<PurchaseOrder>> squarePurchaseOrder;
        squarePurchaseOrder = statsPivotService.getStatisticsBySquare(purchaseOrderService.getCurrentList());
        for (Map.Entry<String, Set<PurchaseOrder>> entry : squarePurchaseOrder.entrySet()) {
            String square = entry.getKey();
            int purchaseOrdersCount = entry.getValue().size();
            int orders = 0;
            Map<String, Integer> tempStatsOrders = statsPivotService.getStatisticsByOrder();
            for (Map.Entry<String, Integer> temp : tempStatsOrders.entrySet()) {
                if (temp.getKey().equals(square)) {
                    orders = temp.getValue();
                }
            }
            int going = 0;
            Map<String, Integer> tempStatsGoing = statsPivotService.getStatisticsBySquareAndGoing();
            for (Map.Entry<String, Integer> temp : tempStatsGoing.entrySet()) {
                if (temp.getKey().equals(square)) {
                    going = temp.getValue();
                }
            }
            int inWork = 0;
            Map<String, Integer> tempStatsInWork = statsPivotService.getStatisticsInWork();
            for (Map.Entry<String, Integer> temp : tempStatsInWork.entrySet()) {
                if (temp.getKey().equals(square)) {
                    inWork = temp.getValue();
                }
            }
            int completedOrders = 0;
            Map<String, Integer> tempStatsCompletedOrders = statsPivotService.getStatisticsBySquareAndComplete();
            for (Map.Entry<String, Integer> temp : tempStatsCompletedOrders.entrySet()) {
                if (temp.getKey().equals(square)) {
                    completedOrders = temp.getValue();
                }
            }
            statsPivotList.add(new StatsPivot(square, purchaseOrdersCount, completedOrders, orders, going, inWork));
        }
        statsPivotList.add(getTotalStatsPivot(statsPivotList));
        statPivotList = statsPivotList;
        return FXCollections.observableArrayList(statsPivotList);
    }

    private StatsPivot getTotalStatsPivot(List<StatsPivot> statsPivotList) {
        int purchaseOrdersCount = 0;
        int orders = 0;
        int going = 0;
        int inWork = 0;
        int completedOrders = 0;
        for (StatsPivot statsPivot : statsPivotList) {
            purchaseOrdersCount += statsPivot.getPurchaseOrders();
            orders += statsPivot.getOrders();
            going += statsPivot.getGoing();
            inWork += statsPivot.getInWork();
            completedOrders += statsPivot.getCompletedOrders();
        }
        return new StatsPivot("Total", purchaseOrdersCount, completedOrders, orders, going, inWork);
    }

    private void fillTableNotPosted() {
        dateNotpostedColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        purchaseOrderNotpostedColumn.setCellValueFactory(new PropertyValueFactory<>("numberApplication"));
        itemNameNotpostedColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        countNotpostedColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
        unitNotpostedColumn.setCellValueFactory(new PropertyValueFactory<>("unit"));
        commentNotpostedColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));
        managerNorPostedColumn.setCellValueFactory(new PropertyValueFactory<>("manager"));
        squareNotPostedColumn.setCellValueFactory(new PropertyValueFactory<>("square"));
    }

    private void fillTableLate() {
        dateLateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        purchaseOrderLateColumn.setCellValueFactory(new PropertyValueFactory<>("numberApplication"));
        itemNameLateColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        countLateColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
        unitLateColumn.setCellValueFactory(new PropertyValueFactory<>("unit"));
        commentLateColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));
        managerLateColumn.setCellValueFactory(new PropertyValueFactory<>("manager"));
        squareLateColumn.setCellValueFactory(new PropertyValueFactory<>("square"));
    }

    private void fillPivotTable() {
        squarePivotColumn.setCellValueFactory(new PropertyValueFactory<>("square"));
        purchaseOrdersPivotColumn.setCellValueFactory(new PropertyValueFactory<>("purchaseOrders"));
        ordersPivotColumn.setCellValueFactory(new PropertyValueFactory<>("orders"));
        goingPivotColumn.setCellValueFactory(new PropertyValueFactory<>("going"));
        inWorkPivotColumn.setCellValueFactory(new PropertyValueFactory<>("inWork"));
        completePivotColumn.setCellValueFactory(new PropertyValueFactory<>("completedOrders"));

        pivotTable.setItems(getPivotStats());
    }

    public static void autoResizeColumns(TableView<PurchaseOrder> table) {
        //Set the right policy
        table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        table.getColumns().forEach((column) -> {
            //Minimal width = columnheader
            Text t = new Text(column.getText());
            double max = t.getLayoutBounds().getWidth();
            for (int i = 0; i < table.getItems().size(); i++) {
                //cell must not be empty
                if (column.getCellData(i) != null) {
                    t = new Text(column.getCellData(i).toString());
                    double calcwidth = t.getLayoutBounds().getWidth();
                    //remember new max-width
                    if (calcwidth > max) {
                        max = calcwidth;
                    }
                }
            }
            //set the new max-wight with some extra space
            column.setPrefWidth(max + 10.0d);
        });
    }

}
