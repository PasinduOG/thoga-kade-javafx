package edu.icet.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemDto {
    private String code;
    private String description;
    private String category;
    private Integer qty;
    private Double unitPrice;
}
