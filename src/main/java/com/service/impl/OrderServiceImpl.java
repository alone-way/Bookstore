package com.service.impl;

import com.dao.BookDao;
import com.dao.OrderDao;
import com.dao.OrderItemDao;
import com.dao.impl.BookDaoImpl;
import com.dao.impl.OrderDaoImpl;
import com.dao.impl.OrderItemDaoImpl;
import com.pojo.*;
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
        return orderDao.queryOrderDetailByOrderId(orderId);
    }

    @Override
    public Order queryOrderByOrderId(String orderId) {
        return orderDao.queryOrderByOrderId(orderId);
    }

    @Override
    public List<Order> queryMyOrders(int userId) {
        return orderDao.queryMyOrders(userId);
    }

    @Override
    public void sendOrder(String orderId) {
        orderDao.changeOrderStatus(orderId, 1);
    }

    @Override
    public void receiveOrder(String orderId, Integer userId) {
        //检查订单状态 (已发货状态才能收货)
        int status = orderDao.queryOrderStatus(orderId);
        if (status == 1) {
            //确认收货
            orderDao.changeOrderStatus(orderId, 2);
        }
    }

    @Override
    public Page<Order> page(int pageNo, int pageSize) {
        Page<Order> page = new Page<>();
        //1.设置总记录数
        int pageTotalCount = orderDao.queryTotalCount();
        page.setPageTotalCount(pageTotalCount);
        //2.设置页大小
        page.setPageSize(pageSize);
        //3.设置总页数
        int pageTotal = (int) Math.ceil(pageTotalCount * 1.0 / pageSize);
        page.setPageTotal(pageTotal);
        //3.设置页码
        page.setPageNo(pageNo);
        //4.求当前页数据
        int begin = (page.getPageNo() - 1) * page.getPageSize(); //起始索引
        List<Order> orders = orderDao.queryOrdersByLimit(begin, page.getPageSize());
        page.setItems(orders);
        return page;
    }
}
