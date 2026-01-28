package edu.icet.repository;

import edu.icet.model.entity.CustomerEntity;

import java.util.List;

public interface CustomerRepository {
    List<CustomerEntity> findAll();
    void save(CustomerEntity customerEntity);
    void update(CustomerEntity customerEntity);
    void deleteById(String customerId);
}
