package edu.icet.controller;

import edu.icet.service.ItemService;
import edu.icet.db.DBConnection;
import edu.icet.model.dto.ItemDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemController implements ItemService {
    private final ObservableList<ItemDto> items = FXCollections.observableArrayList(
            new ItemDto("I0001", "Apple", "Fruit", 10, 250.0),
            new ItemDto("I0002", "Orange", "Fruit", 5, 350.0)
    );

    String generateItemId() {
        if (items.isEmpty()) {
            return "I0001";
        }
        String lastItemId = items.get(items.size() - 1).getCode();
        int lastNumber = Integer.parseInt(lastItemId.substring(1));
        return String.format("I%04d", lastNumber + 1);
    }

    @Override
    public ObservableList<ItemDto> getArrayList() {
        return items;
    }

    @Override
    public void loadData() {
        items.clear();
        try {
            PreparedStatement preparedStatement = DBConnection.getInstance().connection().prepareStatement("SELECT * FROM item");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ItemDto itemDto = new ItemDto(
                        resultSet.getString("code"),
                        resultSet.getString("description"),
                        resultSet.getString("category"),
                        resultSet.getInt("qty"),
                        resultSet.getDouble("unit_price")
                );
                items.add(itemDto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addItem(String code, String description, String category, int qty, double unitPrice) {
        try {
            PreparedStatement preparedStatement = DBConnection.getInstance().connection().prepareStatement("INSERT INTO item VALUES (?,?,?,?,?)");
            preparedStatement.setObject(1, code);
            preparedStatement.setObject(2, description);
            preparedStatement.setObject(3, category);
            preparedStatement.setObject(4, qty);
            preparedStatement.setObject(5, unitPrice);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateItem(String code, String description, String category, int qty, double unitPrice) {
        try {
            PreparedStatement preparedStatement = DBConnection.getInstance().connection().prepareStatement("UPDATE item SET description = ?, category = ?, qty = ?, unit_price = ? WHERE code = ?");
            preparedStatement.setObject(1, description);
            preparedStatement.setObject(2, category);
            preparedStatement.setObject(3, qty);
            preparedStatement.setObject(4, unitPrice);
            preparedStatement.setObject(5, code);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteItem(String code) {
        try{
            PreparedStatement preparedStatement = DBConnection.getInstance().connection().prepareStatement("DELETE FROM item WHERE code = ?");
            preparedStatement.setObject(1, code);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
