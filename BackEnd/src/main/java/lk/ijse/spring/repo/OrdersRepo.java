package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * `@authority Tharindu Dilan`
 * 8:30 PM
 * 2023-10-09 - 10 - 2023
 */
public interface OrdersRepo extends JpaRepository<Orders,String> {
}
