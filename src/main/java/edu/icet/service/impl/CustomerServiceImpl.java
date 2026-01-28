package edu.icet.service.impl;

import edu.icet.model.dto.CustomerDto;
import edu.icet.model.entity.CustomerEntity;
import edu.icet.repository.CustomerRepository;
import edu.icet.repository.impl.CustomerRepositoryImpl;
import edu.icet.service.CustomerService;

import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository = new CustomerRepositoryImpl();

    @Override
    public String generateCustomerId() {
        List<CustomerDto> customers = getArrayList();
        if (customers.isEmpty()) {
            return "C001";
        }
        String lastCustomerId = customers.get(customers.size() - 1).getId();
        int lastNumber = Integer.parseInt(lastCustomerId.substring(1));
        return String.format("C%03d", lastNumber + 1);
    }

    @Override
    public List<CustomerDto> getArrayList() {
        List<CustomerDto> customers = new ArrayList<>();
        repository.findAll().forEach(entity -> {
            CustomerDto dto = new CustomerDto(
                    entity.getId(),
                    entity.getTitle(),
                    entity.getName(),
                    entity.getDob(),
                    entity.getSalary(),
                    entity.getAddress(),
                    entity.getCity(),
                    entity.getProvince(),
                    entity.getPostalCode()
            );
            customers.add(dto);
        });
        return customers;
    }

    @Override
    public void addCustomer(CustomerDto customerDto) {
        CustomerEntity entity = new CustomerEntity(
                customerDto.getId(),
                customerDto.getTitle(),
                customerDto.getName(),
                customerDto.getDob(),
                customerDto.getSalary(),
                customerDto.getAddress(),
                customerDto.getCity(),
                customerDto.getProvince(),
                customerDto.getPostalCode()
        );
        repository.save(entity);
    }

    @Override
    public void updateCustomer(CustomerDto customerDto) {
        CustomerEntity entity = new CustomerEntity(
                customerDto.getId(),
                customerDto.getTitle(),
                customerDto.getName(),
                customerDto.getDob(),
                customerDto.getSalary(),
                customerDto.getAddress(),
                customerDto.getCity(),
                customerDto.getProvince(),
                customerDto.getPostalCode()
        );
        repository.update(entity);
    }

    @Override
    public void deleteCustomer(String customerId) {
        repository.deleteById(customerId);
    }
}
