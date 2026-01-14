package edu.icet.controller;

import edu.icet.model.Item;
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

public class ItemFormController {

    private final Stage stage = new Stage();

    private final ObservableList<Item> items = FXCollections.observableArrayList(
            new Item("I0001", "Apple", "Fruit", 10, 250.0),
            new Item("I0002", "Orange", "Fruit", 5, 350.0)
    );
    private String itemId = generateItemId();

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
    private TableView<Item> tblItemDetails;

    @FXML
    private TextField txtCategory;

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
        GaussianBlur blur = new GaussianBlur(10);
        rootPane.setEffect(blur);
        txtItemCode.setText(itemId);

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

        tblItemDetails.setItems(items);

        tblItemDetails.getSelectionModel().selectedItemProperty().addListener(((observableValue, item, newValue) -> {
            if (newValue != null) {
                txtItemCode.setText(newValue.getCode());
                txtDescription.setText(newValue.getDescription());
                txtCategory.setText(newValue.getCategory());
                txtQty.setText(String.valueOf(newValue.getQty()));
                txtUnitPrice.setText(String.valueOf(newValue.getUnitPrice()));
            }
        }));
    }

    void clear() {
        itemId = generateItemId();
        txtItemCode.setText(itemId);
        txtDescription.clear();
        txtCategory.clear();
        txtQty.clear();
        txtUnitPrice.clear();
        tblItemDetails.getSelectionModel().clearSelection();
    }

    String generateItemId() {
        if (items.isEmpty()) {
            return "I0001";
        }
        String lastItemId = items.get(items.size()-1).getCode();
        int lastNumber = Integer.parseInt(lastItemId.substring(1));
        return String.format("I%04d", lastNumber + 1);
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String description = txtDescription.getText();
        String category = txtCategory.getText();
        Integer qty = Integer.parseInt(txtQty.getText());
        Double unitPrice = Double.parseDouble(txtUnitPrice.getText());

        Item item = new Item(itemId, description, category, qty, unitPrice);
        items.add(item);

        itemId = generateItemId();
        txtItemCode.setText(itemId);
        clear();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clear();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        int getCustomerIndex = tblItemDetails.getSelectionModel().getSelectedIndex();
        items.remove(getCustomerIndex);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        Item getCustomerItem = tblItemDetails.getSelectionModel().getSelectedItem();
        getCustomerItem.setDescription(txtDescription.getText());
        getCustomerItem.setCategory(txtCategory.getText());
        getCustomerItem.setQty(Integer.parseInt(txtQty.getText()));
        getCustomerItem.setUnitPrice(Double.parseDouble(txtUnitPrice.getText()));
        tblItemDetails.refresh();
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
