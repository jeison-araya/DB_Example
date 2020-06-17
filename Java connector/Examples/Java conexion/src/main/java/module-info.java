module TEST.DB {
    requires javafx.controls;
    requires javafx.base;
    requires java.sql;
    requires mysql.connector.java;
    exports com.jeison.araya.examples.db.example.ui to javafx.graphics;
}