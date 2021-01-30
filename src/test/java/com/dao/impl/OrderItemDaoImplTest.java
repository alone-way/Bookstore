package com.dao.impl;

import com.dao.OrderItemDao;
import com.pojo.OrderItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description
 * @Author OneIce
 * @Date 2021/1/21 17:53
 */
class OrderItemDaoImplTest {
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();

    @Test
    void saveOrderItem() {
        orderItemDao.saveOrderItem(new OrderItem(null, "java SE", 1, BigDecimal.valueOf(50), "12345678"));
        orderItemDao.saveOrderItem(new OrderItem(null, "Python", 2, BigDecimal.valueOf(20), "12345678"));
        orderItemDao.saveOrderItem(new OrderItem(null, "C++", 3, BigDecimal.valueOf(10), "12345678"));
    }

    @Test
    void queryOrderDetailByOrderId() {
        List<OrderItem> orderItems = orderItemDao.queryOrderDetailByOrderId("12345678");
        orderItems.forEach(System.out::println);
    }
}
