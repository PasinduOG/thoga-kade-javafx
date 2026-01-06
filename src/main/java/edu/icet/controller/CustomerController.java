package edu.icet.controller;

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
import javafx.collections.FXCollections;
import edu.icet.model.Customer;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class CustomerController {

    private final Stage stage = new Stage();

    private final ObservableList<Customer> customers = FXCollections.observableArrayList(
            new Customer("C0001", "Mr", "Pasindu", "2002-09-08", 120000.0, "Horawala", "Matugama", "Western", "12108"),
            new Customer("C0002", "Mr", "Pramuditha", "2004-07-18", 75000.0, "Raigama", "Bandaragama", "Western", "23432")
    );
    private String customerId = generateCustomerId();

    @FXML
    private ImageView rootPane;

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
    private TableColumn<?, ?> colCustomerType;

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
    private TableView<Customer> tblCustomerDetails;

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
    void initialize() {
        GaussianBlur blur = new GaussianBlur(10);
        rootPane.setEffect(blur);

        cBoxCustomerType.setItems(FXCollections.observableArrayList(
                "Mr", "Mrs", "Miss"
        ));

        txtCustomerId.setText(customerId);

        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCustomerType.setCellValueFactory(new PropertyValueFactory<>("title"));
        colDateOfBirth.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colCustomerSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        tblCustomerDetails.setItems(customers);

        tblCustomerDetails.getSelectionModel().selectedItemProperty().addListener(((observableValue, customer, newValue) -> {
            if (newValue != null) {
                txtCustomerId.setText(newValue.getId());
                cBoxCustomerType.setValue(newValue.getTitle());
                txtCustomerName.setText(newValue.getName());
                dateBoxBirthday.setValue(LocalDate.parse(newValue.getDob()));
                txtCustomerSalary.setText(Double.toString(newValue.getSalary()));
                txtCustomerAddress.setText(newValue.getAddress());
                txtCity.setText(newValue.getCity());
                txtProvince.setText(newValue.getProvince());
                txtPostalCode.setText(newValue.getPostalCode());
            }
        }));
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String type = cBoxCustomerType.getValue();
        String name = txtCustomerName.getText();
        String dob = String.valueOf(dateBoxBirthday.getValue());
        Double salary = Double.parseDouble(txtCustomerSalary.getText());
        String address = txtCustomerAddress.getText();
        String city = txtCity.getText();
        String province = txtProvince.getText();
        String postalCode = txtPostalCode.getText();

        Customer customer = new Customer(customerId, type, name, dob, salary, address, city, province, postalCode);
        customers.add(customer);

        customerId = generateCustomerId();
        txtCustomerId.setText(customerId);
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clear();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        int getCustomerIndex = tblCustomerDetails.getSelectionModel().getSelectedIndex();
        customers.remove(getCustomerIndex);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        Customer getCustomerItem = tblCustomerDetails.getSelectionModel().getSelectedItem();
        getCustomerItem.setTitle(cBoxCustomerType.getValue());
        getCustomerItem.setName(txtCustomerName.getText());
        getCustomerItem.setDob(String.valueOf(dateBoxBirthday.getValue()));
        getCustomerItem.setSalary(Double.parseDouble(txtCustomerSalary.getText()));
        getCustomerItem.setAddress(txtCustomerAddress.getText());
        getCustomerItem.setCity(txtCity.getText());
        getCustomerItem.setProvince(txtProvince.getText());
        getCustomerItem.setPostalCode(txtPostalCode.getText());
        tblCustomerDetails.refresh();
    }

    String generateCustomerId() {
        if (customers.isEmpty()) {
            return "C0001";
        }
        String lastCustomerId = customers.get(customers.size()-1).getId();
        int lastNumber = Integer.parseInt(lastCustomerId.substring(1));
        return String.format("C%04d", lastNumber + 1);
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

    void clear(){
        customerId = generateCustomerId();
        txtCustomerId.setText(customerId);
        txtCustomerName.clear();
        dateBoxBirthday.setValue(null);
        txtCustomerSalary.clear();
        txtCustomerAddress.clear();
        txtCity.clear();
        txtProvince.clear();
        txtPostalCode.clear();
        tblCustomerDetails.getSelectionModel().clearSelection();
    }
}


