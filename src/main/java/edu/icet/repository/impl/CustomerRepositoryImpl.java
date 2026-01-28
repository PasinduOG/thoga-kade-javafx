package edu.icet.repository.impl;

import edu.icet.db.DBConnection;
import edu.icet.model.entity.CustomerEntity;
import edu.icet.repository.CustomerRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public List<CustomerEntity> findAll() {
        List<CustomerEntity> customerEntities = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = DBConnection.getInstance().connection().prepareStatement("SELECT * FROM customer");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CustomerEntity entity = new CustomerEntity(
                        resultSet.getString("CustId"),
                        resultSet.getString("CustTitle"),
                        resultSet.getString("CustName"),
                        resultSet.getString("DOB"),
                        resultSet.getDouble("salary"),
                        resultSet.getString("CustAddress"),
                        resultSet.getString("City"),
                        resultSet.getString("Province"),
                        resultSet.getString("PostalCode")
                );
                customerEntities.add(entity);
            }
            return customerEntities;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(CustomerEntity customerEntity) {
        try {
            PreparedStatement preparedStatement = DBConnection.getInstance().connection().prepareStatement("INSERT INTO customer VALUES (?,?,?,?,?,?,?,?,?)");
            preparedStatement.setObject(1, customerEntity.getId());
            preparedStatement.setObject(2, customerEntity.getTitle());
            preparedStatement.setObject(3, customerEntity.getName());
            preparedStatement.setObject(4, customerEntity.getDob());
            preparedStatement.setObject(5, customerEntity.getSalary());
            preparedStatement.setObject(6, customerEntity.getAddress());
            preparedStatement.setObject(7, customerEntity.getCity());
            preparedStatement.setObject(8, customerEntity.getProvince());
            preparedStatement.setObject(9, customerEntity.getPostalCode());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(CustomerEntity customerEntity) {
        try {
            PreparedStatement preparedStatement = DBConnection.getInstance().connection().prepareStatement("UPDATE customer SET CustTitle = ?, CustName = ?, DOB = ?, salary = ?, CustAddress = ?, City = ?, Province = ?, PostalCode = ? WHERE CustID = ?");
            preparedStatement.setObject(1, customerEntity.getTitle());
            preparedStatement.setObject(2, customerEntity.getName());
            preparedStatement.setObject(3, customerEntity.getDob());
            preparedStatement.setObject(4, customerEntity.getSalary());
            preparedStatement.setObject(5, customerEntity.getAddress());
            preparedStatement.setObject(6, customerEntity.getCity());
            preparedStatement.setObject(7, customerEntity.getProvince());
            preparedStatement.setObject(8, customerEntity.getPostalCode());
            preparedStatement.setObject(9, customerEntity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(String customerId) {
        try {
            PreparedStatement preparedStatement = DBConnection.getInstance().connection().prepareStatement("DELETE FROM customer WHERE CustID = ?");
            preparedStatement.setObject(1, customerId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
