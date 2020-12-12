package com.drmed.order.controller;

import com.drmed.additional.exceptions.DataNotFoundInDatabase;
import com.drmed.order.dto.NewOrderDto;
import com.drmed.order.dto.OrderDto;
import com.drmed.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping(value = "getOrderById")
    public OrderDto getOrderById(@RequestParam Long orderId) throws DataNotFoundInDatabase {
        return orderService.getOrderDtoById(orderId);
    }

    @GetMapping(value = "getAllOrdersFromPatient")
    public List<OrderDto> getAllOrdersFromPatient(@RequestParam Long patientId) {
        return orderService.getAllOrdersFromPatient(patientId);
    }

    @PostMapping( value = "addOrderForPatient")
    public OrderDto addOrderForPatient(@RequestBody NewOrderDto newOrderDto) throws DataNotFoundInDatabase {
        return orderService.addOrderForPatient(newOrderDto);
    }

    @PutMapping(value = "addTestToOrder")
    public OrderDto addTestToOrder(@RequestParam Long orderId, @RequestParam List<Long> testIdList) throws DataNotFoundInDatabase {
        return orderService.addTestToOrder(orderId, testIdList);
    }

    @PutMapping(value = "cancelOrder")
    public OrderDto cancelOrder(@RequestParam Long orderId) throws DataNotFoundInDatabase {
        return orderService.cancelOrder(orderId);
    }
}
