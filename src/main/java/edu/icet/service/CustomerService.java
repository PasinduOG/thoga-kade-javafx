package edu.icet.service;

import edu.icet.model.dto.CustomerDto;
import javafx.collections.ObservableList;

public interface CustomerService {
    ObservableList<CustomerDto> getArrayList();
    void loadData();
    void addCustomer(String customerId, String type, String name, String dob, Double salary, String address, String city, String province, String postalCode);
    void updateCustomer(String customerId, String type, String name, String dob, Double salary, String address, String city, String province, String postalCode);
    void deleteCustomer(String customerId);
}