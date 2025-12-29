package edu.icet.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Item {
    private String code;
    private String description;
    private String category;
    private Integer qty;
    private Double unitPrice;
}
