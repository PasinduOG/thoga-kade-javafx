package edu.icet.controller;

import edu.icet.model.dto.EmployeeDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class EmployeeFormController {

    private final Stage stage = new Stage();
    private final EmployeeController controller = new EmployeeController();

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
    private ImageView rootPane;

    @FXML
    private TableView<EmployeeDto> tblEmployeeDetails;

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

    @FXML
    void initialize() {
        controller.loadData();
        GaussianBlur blur = new GaussianBlur(10);
        rootPane.setEffect(blur);

        txtEmployeeId.setText(controller.generateEmployeeId());

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

        tblEmployeeDetails.setItems(controller.getArrayList());

        tblEmployeeDetails.getSelectionModel().selectedItemProperty().addListener((observableValue, employeeDto, newValue) -> {
            if(newValue!=null){
                txtEmployeeId.setText(newValue.getId());
                txtEmployeeName.setText(newValue.getName());
                txtEmployeeNic.setText(newValue.getNic());
                dateBoxDateOfBirth.setValue(LocalDate.parse(newValue.getDob()));
                txtEmployeePosition.setText(newValue.getPosition());
                txtEmployeeSalary.setText(String.valueOf(newValue.getSalary()));
                txtContactNumber.setText(newValue.getContactNumber());
                txtEmployeeAddress.setText(newValue.getAddress());
            }
        });
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String employeeId = txtEmployeeId.getText();
        String name = txtEmployeeName.getText();
        String nic = txtEmployeeNic.getText();
        String dob = String.valueOf(dateBoxDateOfBirth.getValue());
        String position = txtEmployeePosition.getText();
        double salary = Double.parseDouble(txtEmployeeSalary.getText());
        String contactNumber = txtContactNumber.getText();
        String address = txtEmployeeAddress.getText();

        controller.addEmployee(employeeId, name, nic, dob, position, salary, contactNumber, address);
        clear();
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
        String id = tblEmployeeDetails.getSelectionModel().getSelectedItem().getId();
        controller.deleteEmployee(id);
        clear();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        EmployeeDto getSelectedItem = tblEmployeeDetails.getSelectionModel().getSelectedItem();
        controller.updateEmployee(
                getSelectedItem.getId(),
                getSelectedItem.getName(),
                getSelectedItem.getNic(),
                getSelectedItem.getDob(),
                getSelectedItem.getPosition(),
                getSelectedItem.getSalary(),
                getSelectedItem.getContactNumber(),
                getSelectedItem.getAddress()
        );
        clear();
    }

    void clear() {
        txtEmployeeId.setText(controller.generateEmployeeId());
        controller.loadData();
        tblEmployeeDetails.refresh();
        txtEmployeeName.clear();
        txtEmployeeNic.clear();
        dateBoxDateOfBirth.setValue(null);
        txtEmployeePosition.clear();
        txtEmployeeSalary.clear();
        txtContactNumber.clear();
        txtEmployeeAddress.clear();
    }

}
