package com.service;

import com.pojo.Cart;
import com.pojo.Order;
import com.pojo.OrderItem;
import com.pojo.Page;

import java.util.List;

/**
 * @Description 订单的服务类
 * @Author OneIce
 * @Date 2021/1/21 21:26
 */
public interface OrderService {
    /**
     * 根据购物车商品为用户创建订单
     * @param cart   用于创建订单的购物车
     * @param userId 创建这个订单的用户id
     * @return 创建的订单号, 创建失败返回空串
     */
    String createOrder(Cart cart, int userId);

    /**
     * 查看订单详情
     * @param orderId 订单id
     * @return 包含订单项的List
     */
    List<OrderItem> orderDetail(String orderId);

    /**
     * 根据订单id查询订单
     * @param orderId 订单id
     * @return 订单对象
     */
    Order queryOrderByOrderId(String orderId);

    /**
     * 查询指定用户的所有订单
     * @param userId 用户id
     * @return 订单
     */
    List<Order> queryMyOrders(int userId);

    /**
     * 订单发货
     * @param orderId 要发货的订单编号
     */
    void sendOrder(String orderId);

    /**
     * 订单收货
     * @param orderId 要收货的订单编号
     * @param user
     */
    void receiveOrder(String orderId, Integer user);

    /** 分页查看指定页码的订单 */
    Page<Order> page(int pageNo, int pageSize);
}
