module com.example.mainproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.mainproject to javafx.fxml;
    exports com.example.mainproject;
}