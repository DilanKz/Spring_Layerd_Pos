package lk.ijse.spring.service;

import lk.ijse.spring.dto.CustomerDTO;

import java.util.List;

/**
 * `@authority Tharindu Dilan`
 * 10:02 AM
 * 2023-10-09 - 10 - 2023
 */
public interface CustomerService {
    void addCustomer(CustomerDTO dto);

    void deleteCustomer(String id);

    List<CustomerDTO> getAllCustomer();

    CustomerDTO findCustomer(String id);

    void updateCustomer(CustomerDTO c);
}
