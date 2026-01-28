package edu.icet.service;

import edu.icet.model.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    String generateCustomerId();
    List<CustomerDto> getArrayList();
    void addCustomer(CustomerDto customerDto);
    void updateCustomer(CustomerDto customerDto);
    void deleteCustomer(String customerId);
}