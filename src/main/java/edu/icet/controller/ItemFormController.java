package edu.icet.controller;

import edu.icet.model.dto.ItemDto;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class ItemFormController {

    private final Stage stage = new Stage();
    private final ItemController controller = new ItemController();

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private ImageView rootPane;

    @FXML
    private TableView<ItemDto> tblItemDetails;

    @FXML
    private ComboBox<String> cBoxCategory;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtItemCode;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    void initialize() {
        controller.loadData();
        GaussianBlur blur = new GaussianBlur(10);
        rootPane.setEffect(blur);
        txtItemCode.setText(controller.generateItemId());
        cBoxCategory.setItems(FXCollections.observableArrayList(
                "Electronics", "Grocery", "Furniture", "Clothing"
        ));

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

        tblItemDetails.setItems(controller.getArrayList());

        tblItemDetails.getSelectionModel().selectedItemProperty().addListener(((observableValue, item, newValue) -> {
            if (newValue != null) {
                txtItemCode.setText(newValue.getCode());
                txtDescription.setText(newValue.getDescription());
                cBoxCategory.setValue(newValue.getCategory());
                txtQty.setText(String.valueOf(newValue.getQty()));
                txtUnitPrice.setText(String.valueOf(newValue.getUnitPrice()));
            }
        }));
    }

    void clear() {
        controller.loadData();
        tblItemDetails.refresh();
        txtItemCode.setText(controller.generateItemId());
        txtDescription.clear();
        txtQty.clear();
        txtUnitPrice.clear();
        tblItemDetails.getSelectionModel().clearSelection();
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String code = txtItemCode.getText();
        String description = txtDescription.getText();
        String category = cBoxCategory.getValue();
        int qty = Integer.parseInt(txtQty.getText());
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());

        controller.addItem(code, description, category, qty, unitPrice);
        clear();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clear();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String code = tblItemDetails.getSelectionModel().getSelectedItem().getCode();
        controller.deleteItem(code);
        clear();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        ItemDto getCustomerItem = tblItemDetails.getSelectionModel().getSelectedItem();
        controller.updateItem(
                getCustomerItem.getCode(),
                getCustomerItem.getDescription(),
                getCustomerItem.getCategory(),
                getCustomerItem.getQty(),
                getCustomerItem.getUnitPrice()
        );
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
}
