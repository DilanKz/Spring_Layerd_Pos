package lk.ijse.spring.repo;

import lk.ijse.spring.dto.SavedOrdersDTO;
import lk.ijse.spring.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * `@authority Tharindu Dilan`
 * 7:49 PM
 * 2023-10-10 - 10 - 2023
 */
public interface QueryRepo extends JpaRepository<Orders, String> {
    @Query(value = "SELECT new SavedOrdersDTO(o.oid, o.date, c.name) FROM Orders o JOIN o.customer c")
    List<SavedOrdersDTO> getAllSavedOrders();
}
