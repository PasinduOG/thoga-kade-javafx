package edu.icet.service.impl;

import edu.icet.db.DBConnection;
import edu.icet.model.dto.SupplierDto;
import edu.icet.service.SupplierService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierServiceImpl implements SupplierService {
    private final ObservableList<SupplierDto> suppliers = FXCollections.observableArrayList();

    String generateSupplierId() {
        if (suppliers.isEmpty()) {
            return "S0001";
        }
        String lastItemId = suppliers.get(suppliers.size()-1).getId();
        int lastNumber = Integer.parseInt(lastItemId.substring(1));
        return String.format("S%04d", lastNumber + 1);
    }

    @Override
    public ObservableList<SupplierDto> getArrayList() {
        return suppliers;
    }

    @Override
    public void loadData() {
        suppliers.clear();
        try {
            PreparedStatement preparedStatement = DBConnection.getInstance().connection().prepareStatement("SELECT * FROM supplier");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                SupplierDto supplierDto = new SupplierDto(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("company_name"),
                        resultSet.getString("address"),
                        resultSet.getString("city"),
                        resultSet.getString("province"),
                        resultSet.getString("postal_code"),
                        resultSet.getString("phone"),
                        resultSet.getString("email")
                );
                suppliers.add(supplierDto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addSupplier(String id, String name, String companyName, String address, String city, String province, String postalCode, String phone, String email) {
        try {
            PreparedStatement preparedStatement = DBConnection.getInstance().connection().prepareStatement("INSERT INTO supplier VALUES (?,?,?,?,?,?,?,?,?)");
            preparedStatement.setObject(1, id);
            preparedStatement.setObject(2, name);
            preparedStatement.setObject(3, companyName);
            preparedStatement.setObject(4, address);
            preparedStatement.setObject(5, city);
            preparedStatement.setObject(6, province);
            preparedStatement.setObject(7, postalCode);
            preparedStatement.setObject(8, phone);
            preparedStatement.setObject(9, email);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateSupplier(String id, String name, String companyName, String address, String city, String province, String postalCode, String phone, String email) {
        try {
            PreparedStatement preparedStatement = DBConnection.getInstance().connection().prepareStatement("UPDATE supplier SET name = ?, company_name = ?, address = ?, city = ?, province = ?, postal_code = ?, phone = ?, email = ? WHERE id = ?");
            preparedStatement.setObject(1, name);
            preparedStatement.setObject(2, companyName);
            preparedStatement.setObject(3, address);
            preparedStatement.setObject(4, city);
            preparedStatement.setObject(5, province);
            preparedStatement.setObject(6, postalCode);
            preparedStatement.setObject(7, phone);
            preparedStatement.setObject(8, email);
            preparedStatement.setObject(9, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteSupplier(String id) {
        try {
            PreparedStatement preparedStatement = DBConnection.getInstance().connection().prepareStatement("DELETE FROM supplier WHERE id = ?");
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
