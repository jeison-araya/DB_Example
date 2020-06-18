package com.jeison.araya.examples.db.example.ui;

import com.jeison.araya.examples.db.example.domain.Student;
import com.jeison.araya.examples.db.example.logic.StudentService;
import com.jeison.araya.examples.db.example.logic.StudentServiceException;
import com.jeison.araya.examples.db.example.logic.StudentServiceImplementation;
import com.jeison.araya.examples.db.example.util.BuilderFX;
import com.jeison.araya.examples.db.example.util.StudentBuilder;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import static com.jeison.araya.examples.db.example.util.BuilderFX.*;
import static com.jeison.araya.examples.db.example.util.UIConstants.*;
import static com.jeison.araya.examples.db.example.util.UIConstants.TABLE_VIEW_DEFAULT_MAX_HEIGHT;

public class StudentForm {
    // Variables \\
    private static TextField institutionalIdTextField;
    private static TextField nameTextField;
    private static TextField phoneTextField;
    private static Button createButton;
    private static Button cancelButton;
    private static GridPane pane;
    private static Scene scene;
    private Stage stage;
    private static StudentService<Student, String> studentService;
    // Constructor \\
    public StudentForm() {
        initializeService();
        //Cargar sistema
        pane = buildPane();
        setupControls(pane);
        addHandlers();
        scene = buildScene(pane);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setScene(scene);
        setStyles();
    }

    private void setStyles() {
        scene.getStylesheets().add("Style.css");
        // Pane
        pane.getStyleClass().add("show-pane");
        // Row Constraints
        pane.setMinSize(GRID_PANE_MIN_WIDTH, GRID_PANE_MIN_HEIGHT);
        // Row #0
        RowConstraints rowConstraints = new RowConstraints(25, 35, 50);
        rowConstraints.setValignment(VPos.CENTER);
        rowConstraints.setVgrow(Priority.SOMETIMES);
        // Row #1
        RowConstraints inputRows = new RowConstraints(40, 40, 60);
        inputRows.setValignment(VPos.CENTER);
        inputRows.setVgrow(Priority.SOMETIMES);
        // Row #2
        RowConstraints rowConstraints2 = new RowConstraints(50, 60, 75);
        rowConstraints2.setValignment(VPos.CENTER);
        rowConstraints2.setVgrow(Priority.ALWAYS);
        // Add Row Constraints
        pane.getRowConstraints().addAll(rowConstraints, inputRows, inputRows, inputRows, rowConstraints2);

        // Columns Constraints
        ColumnConstraints columnConstraints = new ColumnConstraints(100, 150, 175);
        columnConstraints.setHalignment(HPos.LEFT);
        columnConstraints.setHgrow(Priority.ALWAYS);

        ColumnConstraints columnConstraints2 = new ColumnConstraints(100, 150, 200);
        columnConstraints.setHalignment(HPos.RIGHT);
        columnConstraints.setHgrow(Priority.ALWAYS);

        pane.getColumnConstraints().addAll(columnConstraints, columnConstraints2);
        // Columns
        //Button
        setButtonEffect(createButton);
        setButtonEffect(cancelButton);
    }

    private void initializeService() {
        studentService = StudentServiceImplementation.getInstance();
    }

    public void display(){
        stage.showAndWait();
    }
    private Scene buildScene(GridPane pane) {
        return new Scene(pane, 400, 500);
    }


    private GridPane buildPane() {
        return new GridPane();
    }

    private void setupControls(GridPane pane) {
        // Row #0
        buildLabelTitle("Crear estudiante", pane, 0, 0, 2, 1);
        // Row #1
        institutionalIdTextField = BuilderFX.buildTextInput("Carné", pane, 1, 1);
        // Row #2
        nameTextField = BuilderFX.buildTextInput("Nombre", pane, 1, 2);
        // Row #3
        phoneTextField = BuilderFX.buildTextInput("Teléfono", pane, 1, 3);
        // Row #4
        createButton = buildButton("Crear", pane, 0, 4);
        cancelButton = buildButton("Cancelar", pane, 1, 4);
    }

    private void addHandlers() {
        createButton.setOnAction(e -> createAction());
        cancelButton.setOnAction(e -> cancelAction());
    }

    private void cancelAction() {
        stage.close();
    }

    private void createAction() {
        boolean valid = true;
        if (nameTextField.getText()==null || nameTextField.getText().isEmpty()) {
            System.out.println("name empty");
            valid = false;
        }
        if (institutionalIdTextField.getText()==null || institutionalIdTextField.getText().isEmpty()) {
            System.out.println("institutionalId empty");
            valid = false;
        }
        // Create Student
        if(valid){
            Student student = new StudentBuilder()
                    .setName(nameTextField.getText())
                    .setInstitutionalId(institutionalIdTextField.getText())
                    .setPhone(phoneTextField.getText())
                    .build();
            try {
                studentService.create(student);
                stage.close();
            } catch (StudentServiceException e) {
                System.out.println("Error");
            }
        }
    }

}
