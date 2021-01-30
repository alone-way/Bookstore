package com.dao.impl;

import com.dao.OrderDao;
import com.pojo.Order;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Description
 * @Author OneIce
 * @Date 2021/1/21 17:49
 */
class OrderDaoImplTest {
    private OrderDao orderDao = new OrderDaoImpl();

    @Test
    void saveOrder() {
        int i = orderDao.saveOrder(new Order("12345678", new Timestamp(System.currentTimeMillis()),
                BigDecimal.valueOf(50), 0
                , 1));

        System.out.println(i);
    }

    @Test
    void queryMyOrders() {
        List<Order> orders = orderDao.queryMyOrders(1);
        orders.forEach(System.out::println);
    }

    @Test
    void changeOrderStatus() {
        System.out.println("before:");
        queryMyOrders();
        orderDao.changeOrderStatus("12345678", 2);
        System.out.println("after:");
        queryMyOrders();
    }
}
