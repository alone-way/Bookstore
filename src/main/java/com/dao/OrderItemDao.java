package com.dao;

import com.pojo.OrderItem;

import java.util.List;

/**
 * @Description 订单项的DAO类
 * @Author OneIce
 * @Date 2021/1/21 17:19
 */
public interface OrderItemDao {
    /** 保存订单项 */
    int saveOrderItem(OrderItem orderItem) ;

    /** 根据订单编号查询所有订单项 */
    List<OrderItem> queryOrderDetailByOrderId(String orderId) ;
}
