package edu.icet.service.impl;

import edu.icet.db.DBConnection;
import edu.icet.model.dto.EmployeeDto;
import edu.icet.service.EmployeeService;
import edu.icet.util.Status;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class EmployeeServiceImpl implements EmployeeService {
    private final ObservableList<EmployeeDto> employees = FXCollections.observableArrayList();

    String generateEmployeeId() {
        if (employees.isEmpty()) {
            return "E0001";
        }
        String lastItemId = employees.get(employees.size()-1).getId();
        int lastNumber = Integer.parseInt(lastItemId.substring(1));
        return String.format("E%04d", lastNumber + 1);
    }

    @Override
    public void loadData() {
        employees.clear();
        try {
            PreparedStatement preparedStatement = DBConnection.getInstance().connection().prepareStatement("SELECT * FROM Employee");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                EmployeeDto employeeDto = new EmployeeDto(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("nic"),
                        resultSet.getString("dob"),
                        resultSet.getString("position"),
                        resultSet.getDouble("salary"),
                        resultSet.getString("contact_number"),
                        resultSet.getString("address"),
                        LocalDate.parse(resultSet.getString("joined_date")),
                        Status.valueOf(resultSet.getString("status"))
                );
                employees.add(employeeDto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<EmployeeDto> getArrayList() {
        return employees;
    }

    @Override
    public void addEmployee(String employeeId, String name, String nic, String dob, String position, double salary, String contactNumber, String address) {
        try {
            PreparedStatement preparedStatement = DBConnection.getInstance().connection()
                    .prepareStatement("INSERT INTO Employee (id, name, nic, dob, position, salary, contact_number, address, joined_date) VALUES (?,?,?,?,?,?,?,?,?)");

            preparedStatement.setObject(1, employeeId);
            preparedStatement.setObject(2, name);
            preparedStatement.setObject(3, nic);
            preparedStatement.setObject(4, dob);
            preparedStatement.setObject(5, position);
            preparedStatement.setObject(6, salary);
            preparedStatement.setObject(7, contactNumber);
            preparedStatement.setObject(8, address);
            preparedStatement.setObject(9, LocalDate.now().toString());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateEmployee(String employeeId, String name, String nic, String dob, String position, double salary, String contactNumber, String address) {
        try {
            PreparedStatement preparedStatement = DBConnection.getInstance().connection()
                    .prepareStatement("UPDATE Employee SET name = ?, nic = ?, dob = ?, position = ?, salary = ?, contactNumber = ?, address = ? WHERE id = ?");

            preparedStatement.setObject(1, name);
            preparedStatement.setObject(2, nic);
            preparedStatement.setObject(3, dob);
            preparedStatement.setObject(4, position);
            preparedStatement.setObject(5, salary);
            preparedStatement.setObject(6, contactNumber);
            preparedStatement.setObject(7, address);
            preparedStatement.setObject(8, employeeId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteEmployee(String employeeId) {
        try {
            PreparedStatement preparedStatement = DBConnection.getInstance().connection().prepareStatement("DELETE FROM Employee WHERE id = ?");
            preparedStatement.setObject(1, employeeId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
