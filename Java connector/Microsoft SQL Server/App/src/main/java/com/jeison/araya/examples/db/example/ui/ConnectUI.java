package com.jeison.araya.examples.db.example.ui;

import com.jeison.araya.examples.db.example.domain.Student;
import com.jeison.araya.examples.db.example.logic.StudentService;
import com.jeison.araya.examples.db.example.logic.StudentServiceException;
import com.jeison.araya.examples.db.example.logic.StudentServiceImplementation;
import com.jeison.araya.examples.db.example.util.BuilderFX;
import com.jeison.araya.examples.db.example.util.ConnectionDB;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

import java.io.File;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

/**
 * @author Jeison Araya Mena | B90514
 */
public class ConnectUI {
    // Variables \\
    private ConnectionDB connectionDB;
    private static ConnectUI instance;
    private static Connection connection;
    private static StudentService<Student, String> studentService;
    // Components
    private static Button newStudentButton;
    private static TextField searchTextField;
    private static TableView<Student> tableView;
    private TableColumn<Student, String> institutionalIdColumn;
    private TableColumn<Student, String> nameColumn;
    private TableColumn<Student, String> phoneColumn;
    private TableColumn deleteColumn;
    private static GridPane pane;
    private static Scene scene;
    private static Alert confirmationAlert;
    private static Stage stage;

    // Constructor \\
    private ConnectUI(Stage stage) {
        studentService = StudentServiceImplementation.getInstance();
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
    public static ConnectUI getInstance(Stage stage) {
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
        BuilderFX.buildLabelTitle("Estudiantes", pane, 0, 0, 1, 1);
        // Row 1
        newStudentButton = BuilderFX.buildButton("Nuevo", pane, 0, 1);
        searchTextField = BuilderFX.buildTextInput2("Mostrar", pane, 1, 1);
        // Row 2
        tableView = buildTableView(pane, 0, 2, 2, 1);
        institutionalIdColumn = buildTableColumn("Carné", "institucionalId", tableView);
        nameColumn = buildTableColumn("Nombre", "name", tableView);
        phoneColumn = buildTableColumn("Teléfono", "phone", tableView);
        deleteColumn = buildButtonTableColumn("Eliminar", tableView, "deleteIcon.png");
        // None Row
        confirmationAlert = BuilderFX.buildAlertDialog("Conexión a Base de datos", "DataBaseIcon.png");
    }

    /**
     * Create a table view and place it in a pane.
     *
     * @param pane   pane where it will be placed.
     * @param column column where it will be assigned.
     * @param row    row where it will be assigned.
     * @return {@code TableView <>} table view ready to add columns and objects.
     */
    public static TableView<Student> buildTableView(GridPane pane, int column, int row, int numColumns, int numRows) {
        TableView<Student> tableView = new TableView<>();
        pane.add(tableView, column, row, numColumns, numRows);
        return tableView;
    }

    /**
     * Create a column and place it in a display table.
     *
     * @param text      Text of the column.
     * @param tableView Shows the table where the column will be added.
     * @return Column configured and placed.
     * @Param Property Property that identifies the column, with an attribute of the object.
     */
    public TableColumn<Student, String> buildTableColumn(String text, String property, TableView tableView) {
        TableColumn<Student, String> tableColumn = new TableColumn(text);
        tableColumn.setId(property);
        tableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        setEditableColumnTextField(tableColumn);
        tableView.getColumns().add(tableColumn);
        return tableColumn;
    }

    /**
     * Modify the values of this row.
     *
     * @param tableColumn to be editable
     */
    private void setEditableColumnTextField(TableColumn tableColumn) {
        // Variables \\
        tableColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>() {
            @Override
            public void handle(TableColumn.CellEditEvent event) {
                //  Variables  \\
                Integer row = event.getTablePosition().getRow();//índice fila
                Student student = (Student) event.getTableView().getItems().get(row);//Objeto a editar
                String value = (String) event.getNewValue();
                switch (tableColumn.getId()) {
                    case "institutionalId":
                        student.setInstitutionalId(value);
                        break;
                    case "name":
                        student.setName(value);
                        break;
                    case "phone":
                        student.setPhone(value);
                        break;
                }
                editAction(student);// Guardar
            }
        });
    }

    /**
     * Add one button to the table
     *
     * @param label of the column header
     */
    private TableColumn<Student, Void> buildButtonTableColumn(String label, TableView<Student> tableView, String image) {
        TableColumn<Student, Void> tableColumn = new TableColumn(label);
        tableColumn.getStyleClass().add("table-column");
        Callback<TableColumn<Student, Void>, TableCell<Student, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Student, Void> call(final TableColumn<Student, Void> param) {
                final TableCell<Student, Void> cell = new TableCell<>() {
                    private final Button btn = new Button();

                    {// Definir funciones del botón

                        btn.setOnAction((event) -> {
                            Student data = getTableView().getItems().get(getIndex());
                            removeAction(data);
                        });

                        btn.getStyleClass().add("table-buttons");
                        btn.setGraphic(new ImageView(new Image(image)));
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty)
                            setGraphic(null);
                        else
                            setGraphic(btn);
                    }
                };
                return cell;
            }
        };
        tableColumn.setCellFactory(cellFactory);
        tableView.getColumns().add(tableColumn);
        return tableColumn;
    }
    private void removeAction (Student data){
        try {
            studentService.delete(data);
        } catch (StudentServiceException e) {
            e.printStackTrace();// TOOD
        }
    }
    private void addHandlers() {
    }

    private static void editAction(Student student) {
        try {
            studentService.update(student);
        } catch (StudentServiceException e) {
            e.printStackTrace();
        }
    }

    private void connectAction() {
        try {
            // Set connection
            connection = connectionDB.getConnection();
            // Show confirmation
            confirmationAlert.setContentText("Conexión establecida.");

            System.out.println("Conexión con base de datos establecida.");
        } catch (Exception e) {
            // Show error
            confirmationAlert.setContentText(e.getMessage());
            System.out.println("Conexión con base de datos fallida.");
        }
        confirmationAlert.show();
    }

    private void showData() {
        try {

            confirmationAlert.setContentText(String.valueOf(studentService.read("87")));
            studentService.delete(new Student(7, "B90514", "Jeison Araya", "8747-3445"));
        } catch (StudentServiceException e) {
            confirmationAlert.setContentText(e.getMessage());
        }
        confirmationAlert.show();
    }

    public Scene getScene() {
        return scene;
    }
}
