package edu.icet.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Employee {
    private String id;
    private String name;
    private String nic;
    private String dob;
    private String position;
    private Double salary;
    private String contactNumber;
    private String address;
    private String joinedDate;
    private String status;
}
