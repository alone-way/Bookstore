package com.dao.impl;

import com.dao.OrderItemDao;
import com.pojo.OrderItem;

import java.util.List;

/**
 * @Description
 * @Author OneIce
 * @Date 2021/1/21 17:25
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(id,`name`,count,price,total_price,order_id) values(?,?,?,?,?,?)";
        return update(sql, orderItem.getId(), orderItem.getName(), orderItem.getCount(), orderItem.getPrice(),
                orderItem.getTotalPrice(), orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderDetailByOrderId(String orderId) {
        String sql = "select id, `name`, count, price, total_price, order_id from t_order_item where order_id=?";
        return queryForList(sql, OrderItem.class, orderId);
    }
}
