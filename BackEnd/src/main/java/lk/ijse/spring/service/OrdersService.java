package lk.ijse.spring.service;

import lk.ijse.spring.dto.SavedOrdersDTO;

import java.util.List;

/**
 * `@authority Tharindu Dilan`
 * 8:40 PM
 * 2023-10-09 - 10 - 2023
 */
public interface OrdersService {
    List<SavedOrdersDTO> getAllSavedOrders();
}
