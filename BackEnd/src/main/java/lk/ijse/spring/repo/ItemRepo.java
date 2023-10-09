package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * `@authority Tharindu Dilan`
 * 9:21 AM
 * 2023-10-09 - 10 - 2023
 */
public interface ItemRepo extends JpaRepository<Item,String> {
}
