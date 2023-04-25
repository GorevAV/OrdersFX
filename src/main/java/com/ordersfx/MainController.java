package com.ordersfx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private Button allPurchaseOrdersButton;

    @FXML
    private Button managersButton;

    @FXML
    void initialize() {
        managersButton.setOnAction(event -> {
            managersButton.getScene().getWindow().hide();
            switchScene("Statistics.fxml");
        });

        allPurchaseOrdersButton.setOnAction(event -> {
            allPurchaseOrdersButton.getScene().getWindow().hide();
            switchScene("All.fxml");
        });
    }

    private void switchScene(String fxml) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxml));
        try {
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("OrdersFX");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
