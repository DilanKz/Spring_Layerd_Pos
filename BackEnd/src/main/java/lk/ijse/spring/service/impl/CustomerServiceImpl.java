package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.entity.Customer;
import lk.ijse.spring.repo.CustomerRepo;
import lk.ijse.spring.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * `@authority Tharindu Dilan`
 * 10:02 AM
 * 2023-10-09 - 10 - 2023
 */

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    ModelMapper mapper;

    public CustomerServiceImpl() {
        System.out.println("CustomerServiceImpl Instantiated");
    }

    @Override
    public void addCustomer(CustomerDTO dto) {

        if (customerRepo.existsById(dto.getId())) {
            throw new RuntimeException(dto.getId()+" is already exist");
        }

        Customer map = mapper.map(dto, Customer.class);
        customerRepo.save(map);
    }

    @Override
    public void deleteCustomer(String id) {
        if (!customerRepo.existsById(id)) {
            throw new RuntimeException(id+ " Customer is not available, check again.!");
        }
        customerRepo.deleteById(id);
    }

    @Override
    public List<CustomerDTO> getAllCustomer() {
        List<Customer> all = customerRepo.findAll();
        return mapper.map(all, new TypeToken<List<CustomerDTO>>() {
        }.getType());
    }

    @Override
    public CustomerDTO findCustomer(String id) {

        if (!customerRepo.existsById(id)) {
            throw new RuntimeException(id+ " Customer is not available, check again.!");
        }

        Customer customer = customerRepo.findById(id).get();
        return mapper.map(customer,CustomerDTO.class);
    }

    @Override
    public void updateCustomer(CustomerDTO c) {
        if (!customerRepo.existsById(c.getId())) {
            throw new RuntimeException(c.getId()+ " Customer is not available, check again.!");
        }

        Customer map = mapper.map(c, Customer.class);
        customerRepo.save(map);
    }
}
