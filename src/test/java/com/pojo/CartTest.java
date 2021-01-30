package com.pojo;

import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description
 * @Author OneIce
 * @Date 2021/1/20 16:49
 */
class CartTest {
    private Cart cart = new Cart();

    @BeforeEach
    void before() {
        cart.addItem(new CartItem(1, "Java从入门到精通", 1, new BigDecimal(50)));
        cart.addItem(new CartItem(1, "Java从入门到精通", 1, new BigDecimal(50)));
        cart.addItem(new CartItem(2, "数据结构和算法", 1, new BigDecimal(100)));
    }

    @Test
    void addItem() {
        System.out.println(cart);
    }

    @Test
    void delete() {
        cart.delete(1);
        System.out.println(cart);
    }

    @Test
    void clear() {
        cart.clear();
        System.out.println(cart);
    }

    @Test
    void updateCount() {
        cart.updateCount(1, 5);
        System.out.println(cart);
    }
}
