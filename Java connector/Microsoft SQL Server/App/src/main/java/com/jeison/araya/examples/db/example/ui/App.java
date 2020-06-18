package com.jeison.araya.examples.db.example.ui;

import javafx.application.Application;
import javafx.stage.Stage;
/**
 * @author Jeison Araya Mena | B90514
 */
public class App extends Application {
    private Stage stage;
          
        
    @Override
    public void start(Stage stage){
        this.stage = stage;
        ConnectUI connectUI = ConnectUI.getInstance(stage);
        stage.setScene(connectUI.getScene());
        stage.show();
    }
    public void display(){
        launch();
    } 
}
