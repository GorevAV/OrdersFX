package com.ordersfx;

import com.ordersfx.service.PurchaseOrder;
import com.ordersfx.service.PurchaseOrderService;
import com.ordersfx.util.ReadExcelFile;
import com.ordersfx.util.WriteExcelFileAll;
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
import java.util.List;

public class AllController {

    @FXML
    private Button allPurchaseOrders;

    @FXML
    private Button azTwentyOneButton;

    @FXML
    private Button azTwentyThreeButton;

    @FXML
    private Button azTwentyTwoButton;

    @FXML
    private Button krButton;

    @FXML
    private Button twentyThreeButton;

    @FXML
    private Button lateButton;

    @FXML
    private Button notPostedButton;

    @FXML
    private TableColumn<PurchaseOrder, String> categoryColumn;

    @FXML
    private TableColumn<PurchaseOrder, String> commentColumnColumn;

    @FXML
    private TableColumn<PurchaseOrder, String> countColumn;

    @FXML
    private TableColumn<PurchaseOrder, String> customerColumn;

    @FXML
    private TableColumn<PurchaseOrder, String> dateColumn;

    @FXML
    private TableColumn<PurchaseOrder, String> datePPColumn;

    @FXML
    private TableColumn<PurchaseOrder, String> datePtyColumn;

    @FXML
    private TableColumn<PurchaseOrder, String> itemNameColumn;

    @FXML
    private TableColumn<PurchaseOrder, String> krColumn;

    @FXML
    private TableColumn<PurchaseOrder, String> managerColumn;

    @FXML
    private TableColumn<PurchaseOrder, String> numberApplicationColumn;

    @FXML
    private TableColumn<PurchaseOrder, String> orderColumn;

    @FXML
    private TableColumn<PurchaseOrder, String> organisationColumn;

    @FXML
    private TableColumn<PurchaseOrder, String> providerColumn;

    @FXML
    private TableColumn<PurchaseOrder, String> ptyColumn;

    @FXML
    private Button readExcel;

    @FXML
    private Button writeExcel;

    @FXML
    private TableColumn<PurchaseOrder, String> squareColumn;

    @FXML
    private TableView<PurchaseOrder> tablePurchaseOrders;

    @FXML
    private TableColumn<PurchaseOrder, String> unitColumn;

    @FXML
    private TextField keywoordTextField;

    @FXML
    private Button returnButton;

    private List<PurchaseOrder> readFile;
    private PurchaseOrderService purchaseOrderService;

    public AllController() {
    }

    @FXML
    void initialize() {

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
        fillTable();

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

        notPostedButton.setOnAction(actionEvent -> {
            tablePurchaseOrders.setItems(purchaseOrderService.getNotPosted());
            autoResizeColumns(tablePurchaseOrders);
        });

        lateButton.setOnAction(actionEvent -> {
            tablePurchaseOrders.setItems(purchaseOrderService.getLate());
            autoResizeColumns(tablePurchaseOrders);
        });

        allPurchaseOrders.setOnAction(actionEvent -> {
            tablePurchaseOrders.setItems(purchaseOrderService.getPurchaseOrderObsSet());
            autoResizeColumns(tablePurchaseOrders);
        });

        returnButton.setOnAction(actionEvent -> {
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

        azTwentyOneButton.setOnAction(actionEvent -> {
            tablePurchaseOrders.setItems(purchaseOrderService.getAzTwentyOne());
            autoResizeColumns(tablePurchaseOrders);
        });

        azTwentyTwoButton.setOnAction(actionEvent -> {
            tablePurchaseOrders.setItems(purchaseOrderService.getAzTwentyTwo());
            autoResizeColumns(tablePurchaseOrders);

        });

        azTwentyThreeButton.setOnAction(actionEvent -> {
            tablePurchaseOrders.setItems(purchaseOrderService.getAzTwentyThree());
            autoResizeColumns(tablePurchaseOrders);
        });

        krButton.setOnAction(actionEvent -> {
            tablePurchaseOrders.setItems(purchaseOrderService.getKr());
            autoResizeColumns(tablePurchaseOrders);
        });

        twentyThreeButton.setOnAction(actionEvent -> {
            tablePurchaseOrders.setItems(purchaseOrderService.getTwentyThree());
            autoResizeColumns(tablePurchaseOrders);
        });

        searchBarKeywordTextField();
    }

    private void searchBarKeywordTextField() {
        keywoordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                tablePurchaseOrders.setItems(purchaseOrderService.getCurrentObsList());
            } else {
                tablePurchaseOrders.setItems(purchaseOrderService.search(newValue));
            }
        });
    }

    private void fillTable() {
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        numberApplicationColumn.setCellValueFactory(new PropertyValueFactory<>("numberApplication"));
        organisationColumn.setCellValueFactory(new PropertyValueFactory<>("organisation"));
        squareColumn.setCellValueFactory(new PropertyValueFactory<>("square"));
        customerColumn.setCellValueFactory(new PropertyValueFactory<>("customer"));
        itemNameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        krColumn.setCellValueFactory(new PropertyValueFactory<>("kr"));
        countColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
        unitColumn.setCellValueFactory(new PropertyValueFactory<>("unit"));
        providerColumn.setCellValueFactory(new PropertyValueFactory<>("provider"));
        managerColumn.setCellValueFactory(new PropertyValueFactory<>("manager"));
        datePPColumn.setCellValueFactory(new PropertyValueFactory<>("datePP"));
        ptyColumn.setCellValueFactory(new PropertyValueFactory<>("pty"));
        datePtyColumn.setCellValueFactory(new PropertyValueFactory<>("datePty"));
        orderColumn.setCellValueFactory(new PropertyValueFactory<>("order"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        commentColumnColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));
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