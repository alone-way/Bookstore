package com.service.impl;

import com.dao.BookDao;
import com.dao.OrderDao;
import com.dao.OrderItemDao;
import com.dao.impl.BookDaoImpl;
import com.dao.impl.OrderDaoImpl;
import com.dao.impl.OrderItemDaoImpl;
import com.pojo.Book;
import com.pojo.Cart;
import com.pojo.Order;
import com.pojo.OrderItem;
import com.service.OrderService;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Description
 * @Author OneIce
 * @Date 2021/1/21 21:40
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, int userId) {
        //创建订单号
        String orderId = "" + System.currentTimeMillis() + userId;
        //保存订单
        Order order = new Order(orderId, new Timestamp(System.currentTimeMillis()), cart.getTotalPrice(), 0, userId);
        orderDao.saveOrder(order);
        cart.getItems().values().forEach(cartItem -> { //获取购物车中每一个商品项
            //转换成订单项
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(),
                    orderId);
            //保存订单项
            orderItemDao.saveOrderItem(orderItem);
            //更新图书销量和库存
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            int rows = bookDao.updateBook(book);
            if (rows == 0) {
                throw new RuntimeException("更新图书销量和库存失败...");
            }
        });
        //清空购物车
        cart.clear();
        return orderId;
    }


    @Override
    public List<OrderItem> orderDetail(String orderId) {
        return null;
    }

    @Override
    public List<Order> queryMyOrders(int userId) {
        return orderDao.queryMyOrders(userId);
    }

    @Override
    public List<Order> allOrders() {
        return null;
    }

    @Override
    public void sendOrder(String orderId) {

    }

    @Override
    public void receiveOrder(String orderId) {

    }
}
