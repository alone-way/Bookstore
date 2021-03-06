package com.servlet;

import com.pojo.Cart;
import com.pojo.Order;
import com.pojo.OrderItem;
import com.pojo.User;
import com.service.OrderService;
import com.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @Description 处理订单相关请求
 * @Author OneIce
 * @Date 2021/1/21 23:57
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();

    /** 处理结账请求 */
    protected void checkout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            //取出购物车和用户信息
            Cart cart = Objects.requireNonNull((Cart) session.getAttribute("cart"), "购物车为空!");
            User user = Objects.requireNonNull((User) session.getAttribute("user"), "用户未登录!");
            //创建订单
            String orderId = orderService.createOrder(cart, user.getId());
            //订单号添加进Session中
            session.setAttribute("orderId", orderId);
            //重定向到结账成功页面
            resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
        } else {
            resp.sendRedirect(req.getContextPath() + "/user/login.jsp");
        }
    }

    /** 查看我的订单列表 */
    protected void myOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        //取出用户信息
        User user = (User) session.getAttribute("user");
        //根据用户id查询所有订单
        List<Order> orders = orderService.queryMyOrders(user.getId());
        //将订单信息存入Request域
        req.setAttribute("orders", orders);
        //转发到页面列表页面: /pages/order/order.jsp
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req, resp);
    }

    /** 查看订单详情 */
    protected void orderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取订单号
        String orderId = req.getParameter("orderId");
        //查询订单
        Order order = orderService.queryOrderByOrderId(orderId);
        //查询订单的所有订单项
        List<OrderItem> orderItems = orderService.orderDetail(orderId);
        //将订单和订单项信息存入Request域
        req.setAttribute("order", order);
        req.setAttribute("orderItems", orderItems);
        //转发到订单详情页面: /pages/order/order_detail.jsp
        req.getRequestDispatcher("/pages/order/order_detail.jsp").forward(req, resp);
    }

    /** 确认收货 */
    protected void receiveOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取订单号
        String orderId = req.getParameter("orderId");
        //获取用户信息
        User user = (User) req.getSession(false).getAttribute("user");
        //确认收货
        orderService.receiveOrder(orderId, user.getId());
        //重定向到订单详情页面
        resp.sendRedirect(req.getContextPath() + "/OrderServlet?action=orderDetail&" + "orderId=" + orderId);
    }


}
