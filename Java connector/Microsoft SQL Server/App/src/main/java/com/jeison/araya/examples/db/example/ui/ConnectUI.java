package com.jeison.araya.examples.db.example.ui;

import com.jeison.araya.examples.db.example.domain.Student;
import com.jeison.araya.examples.db.example.logic.StudentService;
import com.jeison.araya.examples.db.example.logic.StudentServiceException;
import com.jeison.araya.examples.db.example.logic.StudentServiceImplementation;
import com.jeison.araya.examples.db.example.util.BuilderFX;
import com.jeison.araya.examples.db.example.util.ThreadPool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.xml.catalog.CatalogFeatures;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.jeison.araya.examples.db.example.util.BuilderFX.setButtonEffect;
import static com.jeison.araya.examples.db.example.util.UIConstants.*;

/**
 * @author Jeison Araya Mena | B90514
 */
public class ConnectUI {
    // Variables \\
    private static ConnectUI instance;
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
    private static boolean autoRefresh;
    // Constructor \\
    private ConnectUI(Stage stage) {
        autoRefresh = true;
        studentService = StudentServiceImplementation.getInstance();
        this.stage = stage;
        // UI
        pane = buildPane();
        setupControls(pane);
        addHandlers();
        try {
            fillTable(studentService.read());
        } catch (StudentServiceException e) {
            e.printStackTrace();
        }
        scene = BuildScene(pane);
        setupStyles();
        autoRefresh();
    }

    private Scene BuildScene(GridPane pane) {
        Scene scene = new Scene(pane, 700, 600);
        return scene;
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
        tableView.setEditable(true);
        institutionalIdColumn = buildTableColumn("Carné", "institutionalId", tableView);
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
    public static TableView buildTableView(GridPane pane, int column, int row, int numColumns, int numRows) {
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
    public TableColumn buildTableColumn(String text, String property, TableView tableView) {
        TableColumn tableColumn = new TableColumn(text);
        tableColumn.setId(property);
        tableColumn.setCellValueFactory(new PropertyValueFactory(property));
        setEditableColumnTextField(tableColumn);
        tableView.getColumns().add(tableColumn);
        return tableColumn;
    }
    /**
     * Set the styles of the components.
     */
    private void setupStyles() {
        scene.getStylesheets().add("Style.css");
        // Pane
        pane.getStyleClass().add("show-pane");
        // Row Constraints
        pane.setMinSize(GRID_PANE_MIN_WIDTH, GRID_PANE_MIN_HEIGHT);
        // Row #0
        RowConstraints rowConstraints = new RowConstraints(25, 25, 30);
        rowConstraints.setValignment(VPos.TOP);
        rowConstraints.setVgrow(Priority.SOMETIMES);
        // Row #1
        RowConstraints rowConstraints1 = new RowConstraints(40, 40, 60);
        rowConstraints1.setValignment(VPos.CENTER);
        rowConstraints.setVgrow(Priority.SOMETIMES);
        // Row #2
        RowConstraints rowConstraints2 = new RowConstraints(500, 600, 600);
        rowConstraints2.setValignment(VPos.CENTER);
        rowConstraints.setVgrow(Priority.ALWAYS);
        // Add Row Constraints
        pane.getRowConstraints().addAll(rowConstraints, rowConstraints1, rowConstraints2);

        // Columns Constraints
        ColumnConstraints columnConstraints = new ColumnConstraints(300, 300, 300);
        columnConstraints.setHalignment(HPos.LEFT);
        columnConstraints.setHgrow(Priority.SOMETIMES);

        ColumnConstraints columnConstraints2 = new ColumnConstraints(150, 175, 300);
        columnConstraints.setHalignment(HPos.RIGHT);
        columnConstraints.setHgrow(Priority.ALWAYS);

        pane.getColumnConstraints().addAll(columnConstraints, columnConstraints2);
        // Columns
        // InstitutionalID Column
        institutionalIdColumn.setMinWidth(65);
        institutionalIdColumn.setMaxWidth(80);
        // Catalog Column
        nameColumn.setMinWidth(75);
        deleteColumn.getStyleClass().add("table-view-column-buttons");
        deleteColumn.setMaxWidth(60);
        deleteColumn.setMinWidth(60);
        // Settings for Table View
        tableView.setMinSize(TABLE_VIEW_DEFAULT_MIN_WIDTH, TABLE_VIEW_DEFAULT_MIN_HEIGHT);
        tableView.setMaxSize(TABLE_VIEW_DEFAULT_MAX_WIDTH, TABLE_VIEW_DEFAULT_MAX_HEIGHT);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        //Button
        setButtonEffect(newStudentButton);
        GridPane.setHalignment(newStudentButton, HPos.LEFT);
    }
    /**
     * Modify the values of this row.
     *
     * @param tableColumn to be editable
     */
    private void setEditableColumnTextField(TableColumn tableColumn) {
        // Variables \\
        tableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumn.setOnEditStart(e -> stopRefresh()); // Stops refreshing
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
                continueRefresh();
            }
        });
        tableColumn.setOnEditCancel(e -> continueRefresh());
    }

    /**
     * Fills the table.
     *
     * @param students list of students.
     */
    private static void fillTable(List<Student> students) {
        try {
            ObservableList<Student> list = FXCollections.observableList(students);
            tableView.getItems().setAll(list);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Add one button to the table
     *
     * @param label of the column header
     */
    private TableColumn<Student, Void> buildButtonTableColumn(String label, TableView<Student> tableView, String image) {
        TableColumn<Student, Void> tableColumn = new TableColumn(label);
        Callback<TableColumn<Student, Void>, TableCell<Student, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Student, Void> call(final TableColumn<Student, Void> param) {
                final TableCell<Student, Void> cell = new TableCell<>() {
                    private final Button btn = new Button();

                    {// Definir funciones del botón
                        btn.getStyleClass().add("table-buttons");
                        btn.setGraphic(new ImageView(new Image(image)));

                        btn.setOnAction((event) -> {
                            Student data = getTableView().getItems().get(getIndex());
                            removeAction(data);
                        });
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

    private void removeAction(Student data) {
        try {
            studentService.delete(data);
        } catch (StudentServiceException e) {
            e.printStackTrace();// TOOD
        }
    }

    private void addHandlers() {
        newStudentButton.setOnAction(e -> newStudentAction());
        searchTextField.setOnKeyPressed(e -> showData());
    }

    private void newStudentAction() {
        StudentForm studentForm = new StudentForm();
        studentForm.display();
    }

    private static void editAction(Student student) {
        try {
            studentService.update(student);
        } catch (StudentServiceException e) {
            e.printStackTrace();
        }
    }

    private static void showData() {
        try {

            if (searchTextField.getText() == null || searchTextField.getText().isEmpty()){
                fillTable(studentService.read());

            }
            else {
                fillTable(studentService.read(searchTextField.getText()));         // Read by reference
            }
        } catch (StudentServiceException e) {
            confirmationAlert.setContentText(e.getMessage());
            confirmationAlert.show();
        }


    }

    public static void refresh(){
        showData();
        searchTextField.clear();
    }

    private void autoRefresh(){
        ThreadPool.getPool().submit(() -> {
            while(true) {
                if(autoRefresh) {
                    System.out.println("Actualizando lista...");
                    showData();
                    // Update each second
                }
                Thread.sleep(1000);
            }
        });
    }

    private void continueRefresh(){
        autoRefresh = true;
    }
    private void stopRefresh(){
        autoRefresh = false;

    }
    public Scene getScene() {
        return scene;
    }
}
