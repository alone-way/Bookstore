package com.dao;

import com.pojo.Order;

import java.util.List;

/**
 * @Description 订单DAO类
 * @Author OneIce
 * @Date 2021/1/21 17:11
 */
public interface OrderDao {
    /** 保存订单 */
    int saveOrder(Order order) ;

    /** 根据用户id查询订单 */
    List<Order> queryMyOrders(int userId) ;

    /**
     * 修改订单状态
     * @param orderId 要修改的订单的id
     * @param status  要修改的订单状态, 0未发货, 1已发货, 2已收货
     * @return 受影响的行数
     */
    int changeOrderStatus(String orderId, int status) ;

}
