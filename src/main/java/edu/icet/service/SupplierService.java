package edu.icet.service;

import edu.icet.model.dto.SupplierDto;
import javafx.collections.ObservableList;

public interface SupplierService {
    ObservableList<SupplierDto> getArrayList();
    void loadData();
    void addSupplier(String id, String name, String companyName, String address, String city, String province, String postalCode, String phone, String email);
    void updateSupplier(String id, String name, String companyName, String address, String city, String province, String postalCode, String phone, String email);
    void deleteSupplier(String id);
}
