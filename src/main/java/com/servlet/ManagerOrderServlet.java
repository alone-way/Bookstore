package com.servlet;

import com.pojo.Order;
import com.pojo.Page;
import com.service.OrderService;
import com.service.impl.OrderServiceImpl;
import com.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        String orderId = req.getParameter("orderId");
        //进行发货
        orderService.sendOrder(orderId);
        //获取页码和页大小
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //重定向到订单管理页面
        resp.sendRedirect(req.getContextPath() + "/manager/OrderServlet?action=page&pageNo=" + pageNo + "&pageSize=" + pageSize);
    }

    /** 分页查看所有订单 */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //获取请求参数pageNo和pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //查询指定页码下的订单
        Page<Order> page = orderService.page(pageNo, pageSize);
        page.setUrl("manager/OrderServlet?action=page");
        //保存到request域
        req.setAttribute("page", page);
        //转发到订单管理页面
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req, resp);
    }

}
