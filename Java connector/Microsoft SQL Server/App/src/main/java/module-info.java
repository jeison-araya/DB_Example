module App {
    requires javafx.controls;
    requires javafx.base;
    requires java.sql;
    opens com.jeison.araya.examples.db.example.domain to javafx.base;
    exports com.jeison.araya.examples.db.example.util to com.microsoft.sqlserver.jdbc;
    exports com.jeison.araya.examples.db.example.ui to javafx.graphics;
}