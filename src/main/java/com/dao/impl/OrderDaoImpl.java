package com.dao.impl;

import com.dao.OrderDao;
import com.pojo.Order;

import java.util.List;

/**
 * @Description
 * @Author OneIce
 * @Date 2021/1/21 17:26
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order (order_id,create_time,price,`status`,user_id) values(?,?,?,?,?)";
        return update(sql, order.getOrderId(), order.getCreateTime(), order.getPrice(), order.getStatus(),
                order.getUserId());
    }

    @Override
    public List<Order> queryMyOrders(int userId) {
        String sql = "select order_id,create_time,price,`status`,user_id from t_order where user_id=?";
        return queryForList(sql, Order.class, userId);
    }

    @Override
    public int changeOrderStatus(String orderId, int status) {
        String sql = "update t_order set `status`=? where order_id=?";
        return update(sql, status, orderId);
    }
}
