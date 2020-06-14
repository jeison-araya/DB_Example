package com.jeison.araya.examples.db.example.ui;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage){
        ConnectUI connectUI = ConnectUI.getInstance(stage);

        stage.setScene(connectUI.getScene());
        stage.show();
    }
}
