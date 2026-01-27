package edu.icet.service;

import edu.icet.model.dto.ItemDto;
import javafx.collections.ObservableList;

public interface ItemService {
    String generateItemId();
    ObservableList<ItemDto> getArrayList();
    void loadData();
    void addItem(String code, String description, String category, int qty, double unitPrice);
    void updateItem(String code, String description, String category, int qty, double unitPrice);
    void deleteItem(String code);
}
