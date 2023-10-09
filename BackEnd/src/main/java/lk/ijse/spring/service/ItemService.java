package lk.ijse.spring.service;

import lk.ijse.spring.dto.ItemDTO;

import java.util.List;

/**
 * `@authority Tharindu Dilan`
 * 10:11 AM
 * 2023-10-09 - 10 - 2023
 */
public interface ItemService {
    void addItem(ItemDTO dto);

    void deleteItem(String id);

    List<ItemDTO> getAllItem();

    ItemDTO findItem(String id);

    void updateItem(ItemDTO obj);
}
