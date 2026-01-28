package edu.icet.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerEntity {
    private String id;
    private String title;
    private String name;
    private String dob;
    private Double salary;
    private String address;
    private String city;
    private String province;
    private String postalCode;
}
