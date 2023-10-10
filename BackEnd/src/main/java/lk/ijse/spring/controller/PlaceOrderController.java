package lk.ijse.spring.controller;

import lk.ijse.spring.dto.OrdersDTO;
import lk.ijse.spring.service.PlaceOrderService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * `@authority Tharindu Dilan`
 * 7:48 PM
 * 2023-10-09 - 10 - 2023
 */
@RestController
@RequestMapping("/placeOrder")
@CrossOrigin
public class PlaceOrderController {
    @Autowired
    PlaceOrderService service;

    public ResponseUtil saveOrder(@RequestBody OrdersDTO dto){
        service.addOrder(dto);
        return new ResponseUtil("Ok","Successfully placed order",dto.getOid());
    }

}
