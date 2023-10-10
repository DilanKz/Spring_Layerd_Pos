package lk.ijse.spring.controller;

import lk.ijse.spring.service.OrdersService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * `@authority Tharindu Dilan`
 * 7:48 PM
 * 2023-10-09 - 10 - 2023
 */
@RestController
@RequestMapping("/orders")
@CrossOrigin
public class OrderController {
    @Autowired
    OrdersService ordersService;

    @GetMapping
    public ResponseUtil getAllOrders(){
        return new ResponseUtil("ok","Successfully fetched all Orders",ordersService.getAllSavedOrders());
    }
}
