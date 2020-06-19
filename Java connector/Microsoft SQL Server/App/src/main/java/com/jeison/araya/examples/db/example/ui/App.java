package com.jeison.araya.examples.db.example.ui;

import javafx.application.Application;
import javafx.stage.Stage;
/**
 * @author Jeison Araya Mena | B90514
 * @version 2.0 [19-06-2020]
 */
public class App extends Application {
    private Stage stage;
          
        
    @Override
    public void start(Stage stage){
        this.stage = stage;
        StudentsTable studentsTable = StudentsTable.getInstance(stage);
        stage.setScene(studentsTable.getScene());
        stage.show();
    }
    public void display(){
        launch();
    } 
}
