package edu.icet.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class CustomerDto {
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
