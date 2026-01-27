package edu.icet.controller;

import edu.icet.model.dto.SupplierDto;
import edu.icet.service.SupplierService;
import edu.icet.service.impl.SupplierServiceImpl;
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

public class SupplierFormController {

    private final Stage stage = new Stage();
    private final SupplierService service = new SupplierServiceImpl();

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
    private TableView<SupplierDto> tblSupplierDetails;

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

        txtSupplierId.setText(service.generateSupplierId());

        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCompanyName.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        colSupplierAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        tblSupplierDetails.setItems(service.getArrayList());

        tblSupplierDetails.getSelectionModel().selectedItemProperty().addListener(((observableValue, supplierDto, newValue) -> {
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

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String id = txtSupplierId.getText();
        String name = txtSupplierName.getText();
        String companyName = txtCompanyName.getText();
        String address = txtSupplierAddress.getText();
        String city = txtCity.getText();
        String province = txtProvince.getText();
        String postalCode = txtPostalCode.getText();
        String phone = txtPhoneNumber.getText();
        String email = txtEmail.getText();

        service.addSupplier(id, name, companyName, address, city, province, postalCode, phone, email);
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
        String getSupplierId = tblSupplierDetails.getSelectionModel().getSelectedItem().getId();
        service.deleteSupplier(getSupplierId);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        SupplierDto getSupplierItem = tblSupplierDetails.getSelectionModel().getSelectedItem();
        service.updateSupplier(
                getSupplierItem.getId(),
                getSupplierItem.getName(),
                getSupplierItem.getCompanyName(),
                getSupplierItem.getAddress(),
                getSupplierItem.getCity(),
                getSupplierItem.getProvince(),
                getSupplierItem.getPostalCode(),
                getSupplierItem.getPhone(),
                getSupplierItem.getEmail()
        );
        clear();
    }

    void clear(){
        tblSupplierDetails.refresh();
        service.loadData();
        txtSupplierId.setText(service.generateSupplierId());
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
