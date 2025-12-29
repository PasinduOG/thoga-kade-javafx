package edu.icet.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import edu.icet.model.Customer;
import javafx.collections.ObservableList;

public class CustomerController {

    ObservableList<Customer> customers = FXCollections.observableArrayList(
            new Customer("I0001", "Mr", "Pasindu", "2002-09-08", 95000.0, "68,Andunwenna Rd, Horawala", "Matugama", "Western", "12108"),
            new Customer("I0002", "Mr", "Kavindu", "2002-09-08", 95000.0, "68,Andunwenna Rd, Horawala", "Matugama", "Western", "12108")
    );

    private final String CUSTOMER_ID = generateCustomerId();

    @FXML
    private ComboBox<String> cBoxCustomerType;

    @FXML
    private TableColumn<?, ?> colCity;

    @FXML
    private TableColumn<?, ?> colCustomerAddress;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colCustomerName;

    @FXML
    private TableColumn<?, ?> colCustomerSalary;

    @FXML
    private TableColumn<?, ?> colDateOfBirth;

    @FXML
    private TableColumn<?, ?> colPostalCode;

    @FXML
    private TableColumn<?, ?> colProvince;

    @FXML
    private DatePicker dateBoxBirthday;

    @FXML
    private TableView<?> tblCustomerDetails;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtCustomerAddress;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtCustomerSalary;

    @FXML
    private TextField txtPostalCode;

    @FXML
    private TextField txtProvince;

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    private void initialize() {
        cBoxCustomerType.setItems(FXCollections.observableArrayList(
                "Mr", "Mrs", "Miss"
        ));
    }

    private String generateCustomerId() {
        int length = customers.toArray().length;
        if (length == 0) {
            return "C0001";
        }
        return String.format("C%04d", length + 1);
    }
}


