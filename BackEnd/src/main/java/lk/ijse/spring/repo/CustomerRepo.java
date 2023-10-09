package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * `@authority Tharindu Dilan`
 * 5:02 PM
 * 2023-10-02 - 10 - 2023
 */
public interface CustomerRepo extends JpaRepository<Customer,String> {
}
