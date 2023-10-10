package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.SavedOrdersDTO;
import lk.ijse.spring.repo.OrdersRepo;
import lk.ijse.spring.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * `@authority Tharindu Dilan`
 * 8:11 PM
 * 2023-10-10 - 10 - 2023
 */
@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    OrdersRepo ordersRepo;

    @Override
    public List<SavedOrdersDTO> getAllSavedOrders(){
        return null;
//        return ordersRepo.getAllSavedOrders();
    }

}
