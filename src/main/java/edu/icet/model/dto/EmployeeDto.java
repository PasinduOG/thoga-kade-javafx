package edu.icet.model.dto;

import edu.icet.util.Status;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class EmployeeDto {
    private String id;
    private String name;
    private String nic;
    private String dob;
    private String position;
    private Double salary;
    private String contactNumber;
    private String address;
    private LocalDate joinedDate;
    private Status status;

    public EmployeeDto(String id, String name, String nic, String dob, String position, Double salary, String contactNumber, String address) {
        this.id = id;
        this.name = name;
        this.nic = nic;
        this.dob = dob;
        this.position = position;
        this.salary = salary;
        this.contactNumber = contactNumber;
        this.address = address;
        this.joinedDate = LocalDate.now();
        this.status = Status.ACTIVE;
    }
}
