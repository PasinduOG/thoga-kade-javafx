package edu.icet.controller;

import edu.icet.controller.service.CustomerService;
import edu.icet.db.DBConnection;
import edu.icet.model.dto.CustomerDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerController implements CustomerService {

    private final ObservableList<CustomerDto> customers = FXCollections.observableArrayList();

    public String generateCustomerId() {
        if (customers.isEmpty()) {
            return "C001";
        }
        String lastCustomerId = customers.get(customers.size() - 1).getId();
        int lastNumber = Integer.parseInt(lastCustomerId.substring(1));
        return String.format("C%03d", lastNumber + 1);
    }

    @Override
    public ObservableList<CustomerDto> getArrayList(){
        return customers;
    }

    @Override
    public void loadData(){
        try {
            PreparedStatement statement = DBConnection.getInstance().connection().prepareStatement("SELECT * FROM customer");
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
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addCustomer(String customerId, String type, String name, String dob, Double salary, String address, String city, String province, String postalCode){
        try {
            PreparedStatement preparedStatement = DBConnection.getInstance().connection().prepareStatement("INSERT INTO customer VALUES (?,?,?,?,?,?,?,?,?)");

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

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateCustomer(String customerId, String type, String name, String dob, Double salary, String address, String city, String province, String postalCode){
        try {
            PreparedStatement preparedStatement = DBConnection.getInstance().connection().prepareStatement("UPDATE customer SET CustTitle = ?, CustName = ?, DOB = ?, salary = ?, CustAddress = ?, City = ?, Province = ?, PostalCode = ? WHERE CustID = ?");

            preparedStatement.setObject(1, type);
            preparedStatement.setObject(2, name);
            preparedStatement.setObject(3, dob);
            preparedStatement.setObject(4, salary);
            preparedStatement.setObject(5, address);
            preparedStatement.setObject(6, city);
            preparedStatement.setObject(7, province);
            preparedStatement.setObject(8, postalCode);
            preparedStatement.setObject(9, customerId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteCustomer(String customerId){
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = DBConnection.getInstance().connection().prepareStatement("DELETE FROM customer WHERE CustID = ?");
            preparedStatement.setObject(1, customerId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
