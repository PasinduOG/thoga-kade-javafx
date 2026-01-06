package edu.icet.controller;

import edu.icet.model.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class SupplierController {

    private final Stage stage = new Stage();

    private final ObservableList<Supplier> suppliers = FXCollections.observableArrayList();
    private String supplierId = generateSupplierId();

    @FXML
    private TableColumn<?, ?> colCity;

    @FXML
    private TableColumn<?, ?> colCompanyName;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colPhone;

    @FXML
    private TableColumn<?, ?> colPostalCode;

    @FXML
    private TableColumn<?, ?> colProvince;

    @FXML
    private TableColumn<?, ?> colSupplierAddress;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableColumn<?, ?> colSupplierName;

    @FXML
    private ImageView rootPane;

    @FXML
    private TableView<Supplier> tblSupplierDetails;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtCompanyName;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtPostalCode;

    @FXML
    private TextField txtProvince;

    @FXML
    private TextField txtSupplierAddress;

    @FXML
    private TextField txtSupplierId;

    @FXML
    private TextField txtSupplierName;

    @FXML
    void initialize(){
        GaussianBlur blur = new GaussianBlur(10);
        rootPane.setEffect(blur);

        txtSupplierId.setText(supplierId);

        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCompanyName.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        colSupplierAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        tblSupplierDetails.setItems(suppliers);

        tblSupplierDetails.getSelectionModel().selectedItemProperty().addListener(((observableValue, supplier, newValue) -> {
            if(newValue != null){
                txtSupplierId.setText(newValue.getId());
                txtSupplierName.setText(newValue.getName());
                txtCompanyName.setText(newValue.getCompanyName());
                txtSupplierAddress.setText(newValue.getAddress());
                txtCity.setText(newValue.getCity());
                txtProvince.setText(newValue.getProvince());
                txtPostalCode.setText(newValue.getPostalCode());
                txtPhoneNumber.setText(newValue.getPhone());
                txtEmail.setText(newValue.getEmail());
            }
        }));
    }

    String generateSupplierId() {
        if (suppliers.isEmpty()) {
            return "S0001";
        }
        String lastItemId = suppliers.get(suppliers.size()-1).getId();
        int lastNumber = Integer.parseInt(lastItemId.substring(1));
        return String.format("S%04d", lastNumber + 1);
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String name = txtSupplierName.getText();
        String companyName = txtCompanyName.getText();
        String address = txtSupplierAddress.getText();
        String city = txtCity.getText();
        String province = txtProvince.getText();
        String postalCode = txtPostalCode.getText();
        String phone = txtPhoneNumber.getText();
        String email = txtEmail.getText();

        Supplier supplier = new Supplier(
                supplierId,
                name,
                companyName,
                address,
                city,
                province,
                postalCode,
                phone,
                email
        );
        suppliers.add(supplier);
        supplierId = generateSupplierId();
        txtSupplierId.setText(supplierId);
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
        int getSupplierIndex = tblSupplierDetails.getSelectionModel().getSelectedIndex();
        suppliers.remove(getSupplierIndex);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        Supplier getSupplierItem = tblSupplierDetails.getSelectionModel().getSelectedItem();
        getSupplierItem.setName(txtSupplierName.getText());
        getSupplierItem.setCompanyName(txtCompanyName.getText());
        getSupplierItem.setAddress(txtSupplierAddress.getText());
        getSupplierItem.setCity(txtCity.getText());
        getSupplierItem.setProvince(txtProvince.getText());
        getSupplierItem.setPhone(txtPhoneNumber.getText());
        getSupplierItem.setEmail(txtEmail.getText());
        tblSupplierDetails.refresh();
    }

    void clear(){
        txtSupplierId.setText(supplierId);
        txtSupplierName.clear();
        txtCompanyName.clear();
        txtSupplierAddress.clear();
        txtCity.clear();
        txtProvince.clear();
        txtPostalCode.clear();
        txtPhoneNumber.clear();
        txtEmail.clear();
        tblSupplierDetails.getSelectionModel().clearSelection();
    }

}
