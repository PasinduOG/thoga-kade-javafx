package edu.icet.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Item {
    private String code;
    private String description;
    private String category;
    private Integer qty;
    private Double unitPrice;
}
