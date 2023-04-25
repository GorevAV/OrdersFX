module com.ordersfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.poi.ooxml;


    opens com.ordersfx to javafx.fxml;
    exports com.ordersfx;
    exports com.ordersfx.util;
    opens com.ordersfx.util to javafx.fxml;
    exports com.ordersfx.service;
    opens com.ordersfx.service to javafx.fxml;
}