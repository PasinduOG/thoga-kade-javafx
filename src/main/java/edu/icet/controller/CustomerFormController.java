package edu.icet.controller;

import edu.icet.db.Database;
import edu.icet.model.dto.CustomerDto;
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
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CustomerFormController {

    private final Stage stage = new Stage();

    private final ObservableList<CustomerDto> customers = FXCollections.observableArrayList();
    private String customerId;

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
    private TableView<CustomerDto> tblCustomerDetails;

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

        try {
            PreparedStatement statement = Database.getInstance().prepareStatement("SELECT * FROM customer");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                CustomerDto customerDto = new CustomerDto(
                        resultSet.getString("CustID"),
                        resultSet.getString("CustTitle"),
                        resultSet.getString("custName"),
                        resultSet.getString("DOB"),
                        resultSet.getDouble("salary"),
                        resultSet.getString("CustAddress"),
                        resultSet.getString("City"),
                        resultSet.getString("Province"),
                        resultSet.getString("PostalCode")
                );

                customers.add(customerDto);
                customerId = generateCustomerId();
                txtCustomerId.setText(customerId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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

        try {
            PreparedStatement preparedStatement = Database.getInstance().prepareStatement("INSERT INTO customer VALUES (?,?,?,?,?,?,?,?,?)");

            preparedStatement.setObject(1, customerId);
            preparedStatement.setObject(2, type);
            preparedStatement.setObject(3, name);
            preparedStatement.setObject(4, dob);
            preparedStatement.setObject(5, salary);
            preparedStatement.setObject(6, address);
            preparedStatement.setObject(7, city);
            preparedStatement.setObject(8, province);
            preparedStatement.setObject(9, postalCode);

            preparedStatement.executeUpdate();
            tblCustomerDetails.refresh();

    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
//        Customer getCustomerItem = tblCustomerDetails.getSelectionModel().getSelectedItem();
//        getCustomerItem.setTitle(cBoxCustomerType.getValue());
//        getCustomerItem.setName(txtCustomerName.getText());
//        getCustomerItem.setDob(String.valueOf(dateBoxBirthday.getValue()));
//        getCustomerItem.setSalary(Double.parseDouble(txtCustomerSalary.getText()));
//        getCustomerItem.setAddress(txtCustomerAddress.getText());
//        getCustomerItem.setCity(txtCity.getText());
//        getCustomerItem.setProvince(txtProvince.getText());
//        getCustomerItem.setPostalCode(txtPostalCode.getText());
//        tblCustomerDetails.refresh();
    }

    String generateCustomerId() {
        if (customers.isEmpty()) {
            return "C001";
        }
        String lastCustomerId = customers.get(customers.size() - 1).getId();
        int lastNumber = Integer.parseInt(lastCustomerId.substring(1));
        return String.format("C%03d", lastNumber + 1);
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

    void clear() {
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


