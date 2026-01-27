package edu.icet.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SupplierDto {
    private String id;
    private String name;
    private String companyName;
    private String address;
    private String city;
    private String province;
    private String postalCode;
    private String phone;
    private String email;
}
