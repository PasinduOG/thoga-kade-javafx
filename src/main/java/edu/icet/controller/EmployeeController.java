package edu.icet.controller;

import edu.icet.model.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;

public class EmployeeController {

    private final Stage stage = new Stage();

    private final ObservableList<Employee> employees = FXCollections.observableArrayList();
    private String employeeId = generateEmployeeId();

    @FXML
    private ComboBox<String> cBoxStatus;

    @FXML
    private TableColumn<?, ?> colDateOfBirth;

    @FXML
    private TableColumn<?, ?> colEmployeeAddress;

    @FXML
    private TableColumn<?, ?> colEmployeeNic;

    @FXML
    private TableColumn<?, ?> colJoinedDate;

    @FXML
    private TableColumn<?, ?> colPosition;

    @FXML
    private TableColumn<?, ?> colEmployeeSalary;

    @FXML
    private TableColumn<?, ?> colContactNumber;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colEmployeeName;

    @FXML
    private DatePicker dateBoxDateOfBirth;

    @FXML
    private DatePicker dateBoxJoinedDate;

    @FXML
    private ImageView rootPane;

    @FXML
    private TableView<Employee> tblEmployeeDetails;

    @FXML
    private TextField txtContactNumber;

    @FXML
    private TextField txtEmployeeAddress;

    @FXML
    private TextField txtEmployeeId;

    @FXML
    private TextField txtEmployeeName;

    @FXML
    private TextField txtEmployeeNic;

    @FXML
    private TextField txtEmployeePosition;

    @FXML
    private TextField txtEmployeeSalary;

    String generateEmployeeId() {
        if (employees.isEmpty()) {
            return "S0001";
        }
        String lastItemId = employees.get(employees.size()-1).getId();
        int lastNumber = Integer.parseInt(lastItemId.substring(1));
        return String.format("E%04d", lastNumber + 1);
    }

    @FXML
    void initialize(){
        GaussianBlur blur = new GaussianBlur(10);
        rootPane.setEffect(blur);

        txtEmployeeId.setText(employeeId);

        cBoxStatus.setItems(FXCollections.observableArrayList(
                "Active", "Inactive"
        ));

        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmployeeNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colDateOfBirth.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        colEmployeeSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colEmployeeAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colJoinedDate.setCellValueFactory(new PropertyValueFactory<>("joinedDate"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        tblEmployeeDetails.setItems(employees);
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String name = txtEmployeeName.getText();
        String nic = txtEmployeeNic.getText();
        String dob = String.valueOf(dateBoxDateOfBirth.getValue());
        String position = txtEmployeePosition.getText();
        Double salary = Double.parseDouble(txtEmployeeSalary.getText());
        String contactNumber = txtContactNumber.getText();
        String address = txtEmployeeAddress.getText();
        String joinedDate = String.valueOf(dateBoxJoinedDate.getValue());
        String status = cBoxStatus.getValue();

        Employee employee = new Employee(
                employeeId,
                name,
                nic,
                dob,
                position,
                salary,
                contactNumber,
                address,
                joinedDate,
                status
        );

        employees.add(employee);

        employeeId = generateEmployeeId();
        txtEmployeeId.setText(employeeId);
    }

    @FXML
    void btnBackOnAction(ActionEvent event) {
        Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        oldStage.close();
        stage.show();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clear();
    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        int getSelectedIndex = tblEmployeeDetails.getSelectionModel().getSelectedIndex();
        employees.remove(getSelectedIndex);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        Employee getSelectedItem = tblEmployeeDetails.getSelectionModel().getSelectedItem();
        getSelectedItem.setName(txtEmployeeName.getText());
        getSelectedItem.setNic(txtEmployeeNic.getText());
        getSelectedItem.setDob(String.valueOf(dateBoxDateOfBirth.getValue()));
        getSelectedItem.setPosition(txtEmployeePosition.getText());
        getSelectedItem.setSalary(Double.valueOf(txtEmployeeSalary.getText()));
        getSelectedItem.setContactNumber(txtContactNumber.getText());
        getSelectedItem.setAddress(txtEmployeeAddress.getText());
        getSelectedItem.setJoinedDate(String.valueOf(dateBoxJoinedDate.getValue()));
        getSelectedItem.setStatus(cBoxStatus.getValue());
        tblEmployeeDetails.refresh();
    }

    void clear() {
        txtEmployeeId.setText(employeeId);
        txtEmployeeName.clear();
        txtEmployeeNic.clear();
        dateBoxDateOfBirth.setValue(null);
        txtEmployeePosition.clear();
        txtEmployeeSalary.clear();
        txtContactNumber.clear();
        txtEmployeeAddress.clear();
        cBoxStatus.setValue(null);
    }

}
