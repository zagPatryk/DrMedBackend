package com.drmed.base.order.repository;

import com.drmed.base.additional.exceptions.dataNotFoundInDatabase.OrderNotFoundException;
import com.drmed.base.order.domain.Order;
import com.drmed.base.order.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderRepository {
    @Autowired
    private OrderCrudRepository orderCrudRepository;
    @Autowired
    private OrderMapper orderMapper;

    @Transactional
    public Order getOrderById(Long orderId) throws OrderNotFoundException {
        return orderMapper.mapToOrder(orderCrudRepository.findById(orderId).orElseThrow(OrderNotFoundException::new));
    }

    public List<Order> getAllOrdersForVisit(Long visitId) {
        List<OrderHibernate> orderHibernateList = new ArrayList<>();
        orderCrudRepository.findAllByVisit_Id(visitId).forEach(orderHibernateList::add);
        return orderMapper.mapToOrderList(orderHibernateList);
    }

    public List<Order> getAllByCodeContains(String code) {
        List<OrderHibernate> orderHibernateList = new ArrayList<>();
        orderCrudRepository.findAllByCodeContains(code).forEach(orderHibernateList::add);
        return orderMapper.mapToOrderList(orderHibernateList);
    }

    public Order saveOrder(Order order) {
        OrderHibernate orderHibernate = orderMapper.mapToOrderHibernate(order);
        orderCrudRepository.save(orderHibernate);
        return orderMapper.mapToOrder(orderHibernate);
    }
}