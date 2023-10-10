package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.OrdersDTO;
import lk.ijse.spring.entity.Item;
import lk.ijse.spring.entity.OrderDetails;
import lk.ijse.spring.entity.Orders;
import lk.ijse.spring.repo.CustomerRepo;
import lk.ijse.spring.repo.ItemRepo;
import lk.ijse.spring.repo.OrderDetailsRepo;
import lk.ijse.spring.repo.OrdersRepo;
import lk.ijse.spring.service.PlaceOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * `@authority Tharindu Dilan`
 * 2:05 PM
 * 2023-10-10 - 10 - 2023
 */
@Service
@Transactional
public class PlaceOrderServiceImpl implements PlaceOrderService {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    ItemRepo itemRepo;

    @Autowired
    OrdersRepo ordersRepo;

    @Autowired
    OrderDetailsRepo detailsRepo;

    @Autowired
    ModelMapper mapper;

    @Override
    public void addOrder(OrdersDTO dto){

        Orders orders = mapper.map(dto, Orders.class);
        ordersRepo.save(orders);

        for (OrderDetails details:orders.getOrderDetails()) {
            Item item = itemRepo.findById(details.getItemCode()).get();
            item.setQtyOnHand(item.getQtyOnHand()-details.getQty());
            itemRepo.save(item);
        }
    }

}
