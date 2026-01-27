package edu.icet.controller.service;

import edu.icet.model.dto.EmployeeDto;
import javafx.collections.ObservableList;

public interface EmployeeService {
    void loadData();
    ObservableList<EmployeeDto> getArrayList();
    void addEmployee(String employeeId, String name, String nic, String dob, String position, double salary, String contactNumber, String address);
    void updateEmployee(String employeeId, String name, String nic, String dob, String position, double salary, String contactNumber, String address);
    void deleteEmployee(String employeeId);
}
