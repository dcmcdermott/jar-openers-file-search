module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens org.jaropeners to javafx.fxml;
    exports org.jaropeners;
}