package com.jeison.araya.examples.db.example.ui;

import com.jeison.araya.examples.db.example.util.BuilderFX;
import com.jeison.araya.examples.db.example.util.ConnectionDB;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ConnectUI {
    // Variables \\
    private ConnectionDB connectionDB;
    private static ConnectUI instance;
    private static Button connectButton;
    private static Button showButton;
    private static GridPane pane;
    private static Scene scene;
    private static Alert confirmationAlert;
    private static Stage stage;
    private static Connection connection;
    // Constructor \\
    private ConnectUI(Stage stage) {
        connectionDB = new ConnectionDB();
        this.stage = stage;
        // UI
        pane = buildPane();
        setupControls(pane);
        addHandlers();
        scene = BuildScene(pane);
    }

    private Scene BuildScene(GridPane pane) {
        return new Scene(pane, 600, 500);
    }

    // Singleton Pattern \\

    public static ConnectUI getInstance(Stage stage){
        if (instance == null)
            instance = new ConnectUI(stage);
        return instance;
    }
    // Methods \\
    /**
     * Builds the main pane.
     *
     * @return {@code GridPane} Grid pane configured.
     */
    private GridPane buildPane() {
        GridPane gridPane = new GridPane();
        // more code...
        return gridPane;
    }
    /**
     * Configure and add the required components in the pane.
     *
     * @param pane for add components.
     */
    private void setupControls(GridPane pane) {
        // Row 0
        BuilderFX.buildLabelTitle("Conectar Base de datos", pane, 0, 0, 1, 1);
        // Row 1
        connectButton = BuilderFX.buildButton("Conectar" , pane, 0, 1);
        showButton = BuilderFX.buildButton("Mostrar registros" , pane, 0, 2);
        // None Row
        confirmationAlert = BuilderFX.buildAlertDialog("Conexión a Base de datos", "DataBaseIcon.png");
    }
    private void addHandlers() {
        connectButton.setOnAction(e -> connectAction());
        showButton.setOnAction(e -> showData());
    }

    private void connectAction() {
        try {
            // Set connection
            connection = connectionDB.getConnection();
            // Show confirmation
            confirmationAlert.setContentText("Conexión establecida.");

            System.out.println("Conexión con base de datos establecida.");
        } catch (Exception e){
            // Show error
            confirmationAlert.setContentText(e.getMessage());
            System.out.println("Conexión con base de datos fallida.");
        }
        confirmationAlert.show();
    }

    private void showData() {
        if(connection!=null){
            PreparedStatement preparedStatement;
            ResultSet resultSet;
            try{
                // Prepare statement...
                preparedStatement = connection.prepareStatement("select * from producto");
                // Execute statement...
                resultSet = preparedStatement.executeQuery();
                String values = "";
                while(resultSet.next()){
                    values += "Nombre: \t" + resultSet.getString("nombre") +"\t\t"
                            + "Precio: \t" + resultSet.getString("precio") + "\n";
                }
                System.out.println(values);
                confirmationAlert.setContentText(values);
            }catch (Exception e){
                // Show error
                confirmationAlert.setContentText(e.getMessage());
            }
            confirmationAlert.show();

        }
    }

    public Scene getScene() {
        return scene;
    }
}
