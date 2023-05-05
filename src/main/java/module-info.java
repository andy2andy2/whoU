
module com.example.projectfinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires org.controlsfx.controls;
    requires java.net.http;
    requires java.desktop;

    opens com.example.whoU to javafx.fxml;
    exports com.example.whoU;



}