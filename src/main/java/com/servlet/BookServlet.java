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
import java.util.List;

/**
 * @Description 处理后台的图书相关请求
 * @Author IceCube
 * @Date 2020/12/3 19:55
 */
@WebServlet("/manager/BookServlet")
public class BookServlet extends BaseServlet {
    private final BookService bookService = new BookServiceImpl();

    /** 处理分页请求, 获得Page对象, 然后转发到 /pages/manager/book_manager.jsp */
    public void page(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //1.获取请求参数pageNo和pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2.调用bookService.page(pageNo,pageSize), 获得Page对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("manager/BookServlet"); //设置分页栏的请求地址
        //3.将Page对象保存到request域
        req.setAttribute("page", page);
        //4.将请求转发到页面 /pages/manager/book_manager.jsp
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

    /**
     * 查询所有图书, 然后转发到图书管理页面 /pages/manager/book_manager.jsp
     */
    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.查询全部图书
        List<Book> books = bookService.queryBooks();
        System.out.println(books);
        //2.保存到Request域中
        req.setAttribute("books", books);
        //3.将请求转发到/pages/manager/book_manager.jsp
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

    /** 添加图书, 然后重定向到/book/manager/BookServlet?action=page */
    public void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        //1.获取请求参数, 封装成Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        //2.添加图书
        bookService.addBook(book);
        //3.重定向到/book/manager/BookServlet?action=page?pageNo=尾页, 展示尾页图书
        int pageNo = Integer.parseInt(req.getParameter("pageNo")) + 1;
        resp.sendRedirect(req.getContextPath() + "/manager/BookServlet?action=page&" + "pageNo=" + pageNo);
        //不能使用下面的请求转发
        //req.getRequestDispatcher("/manager/BookServlet?action=page").forward(req, resp);
    }

    /** 根据id删除图书, 然后重定向到原书所在页 */
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //1.获取要删除的图书id
        Integer id = Integer.valueOf(req.getParameter("id"));
        //2.删除图书
        bookService.deleteBookById(id);
        //3.重定向到 /book/pages/manager/BookServlet?action=page&pageNo=, 展示原页码下的图书
        resp.sendRedirect(req.getContextPath() + "/manager/BookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    /** 修改图书的第一步, 根据id获取图书信息, 然后转发到 /pages/manager/book_edit.jsp */
    public void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取要修改的图书id
        Integer id = Integer.valueOf(req.getParameter("id"));
        //2.查询图书, 并保存到request域
        Book book = bookService.queryBookById(id);
        req.setAttribute("book", book);
        //3.转发到 /pages/manager/book_edit.jsp
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }

    /** 修改图书的第二步, 获取图书信息, 修改图书, 然后重定向到 /book/manager/BookServlet?action=page&pageNo=原书页码 */
    public void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //1.获取请求参数, 封装成Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        //2.修改图书
        bookService.updateBook(book);
        //3.重定向到 /book/manager/BookServlet?action=page&pageNo=原书页码
        resp.sendRedirect(req.getContextPath() + "/manager/BookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

}
