package com.service.impl;

import com.pojo.Cart;
import com.pojo.CartItem;
import com.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description
 * @Author OneIce
 * @Date 2021/1/21 23:29
 */
class OrderServiceImplTest {
    private OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "Java从入门到精通", 1, new BigDecimal(50)));
        cart.addItem(new CartItem(1, "Java从入门到精通", 1, new BigDecimal(50)));
        cart.addItem(new CartItem(2, "数据结构和算法", 1, new BigDecimal(100)));
        String orderId = orderService.createOrder(cart, 1);
        System.out.println("订单号:" + orderId);
    }

    @Test
    void orderDetail() {
    }

    @Test
    void queryMyOrder() {
    }

    @Test
    void allOrders() {
    }

    @Test
    void sendOrder() {
    }

    @Test
    void receiveOrder() {
    }
}
