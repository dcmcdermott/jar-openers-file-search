module org.example {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.jaropeners to javafx.fxml;
    exports org.jaropeners;
}