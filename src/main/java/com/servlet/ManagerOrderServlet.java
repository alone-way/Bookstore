package com.servlet;

import com.pojo.Order;
import com.service.OrderService;
import com.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Description 处理订单管理相关请求的Servlet
 * @Author OneIce
 * @Date 2021/2/22 15:12
 */
@WebServlet("/manager/OrderServlet")
public class ManagerOrderServlet extends BaseServlet {

    private OrderService orderService = new OrderServiceImpl();

    /** 订单发货 */
    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取订单编号

        //进行发货

        //重定向到订单管理页面
    }

    /** 查看所有订单 */
    protected void allOrders(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //查询所有订单
        List<Order> orders = orderService.allOrders();
        //保存到request域
        req.setAttribute("orders", orders);
        //转发到订单管理页面
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req, resp);
    }

}
