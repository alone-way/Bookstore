package com.servlet;

import com.pojo.Book;
import com.pojo.Page;
import com.service.BookService;
import com.service.impl.BookServiceImpl;
import com.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description 处理主页上的图书相关请求
 * @Author IceCube
 * @Date 2020/12/13 21:15
 */
@WebServlet("/client/BookServlet")
public class ClientBookServlet extends BaseServlet {
    private final BookService bookService = new BookServiceImpl();

    /** 处理前台的分页请求, 获得Page对象, 然后转发到 /pages/client/index.jsp */
    public void page(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //1.获取请求参数pageNo和pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2.调用bookService.page(pageNo,pageSize), 获得Page对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("client/BookServlet?action=page"); //设置分页栏的请求地址
        //3.将Page对象保存到request域
        req.setAttribute("page", page);
        //4.将请求转发到页面 /pages/client/index.jsp
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

    /** 处理【价格区间搜索】的分页请求，获得Page对象, 然后转发到 /pages/client/index.jsp */
    public void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //1.取出请求参数
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(req.getParameter("min"), 0);
        int max = WebUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);
        //2.搜索价格区间内的图书, 获得Page对象
        Page<Book> page = bookService.pageByPrice(min, max, pageNo, pageSize);
        //设置分页栏所需的url, 别忘了附加min和max参数, 否则切页时就变成全局搜索了
        page.setUrl("client/BookServlet?action=pageByPrice&min=" + min + "&max=" + max);
        //3.将Page对象保存到request域中
        req.setAttribute("page", page);
        //4.转发到/pages/client/index.jsp
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }
}
