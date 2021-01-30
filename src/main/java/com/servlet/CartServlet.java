package com.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.pojo.Book;
import com.pojo.Cart;
import com.pojo.CartItem;
import com.service.BookService;
import com.service.impl.BookServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 处理购物车相关的请求
 * @Author OneIce
 * @Date 2021/1/20 17:45
 */
@WebServlet("/CartServlet")
public class CartServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    /** 处理增加商品项的请求, */
    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        int bookId = Integer.parseInt(req.getParameter("bookId")); //获取图书编号
        Cart cart = (Cart) session.getAttribute("cart");
        //第一次增加商品项时需要在Session中创建购物车
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        //获取图书信息
        Book book = bookService.queryBookById(bookId);
        //将图书信息转换成商品项
        CartItem cartItem = new CartItem(bookId, book.getName(), 1, BigDecimal.valueOf(book.getPrice()));
        //图书加入购物车
        cart.addItem(cartItem);
        session.setAttribute("lastItemName", book.getName());
        //将购物车商品数量和图书名称以Json形式返回
        resp.setContentType("application/json");
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("totalCount", new JsonPrimitive(cart.getTotalCount()));
        jsonObject.add("lastItemName", new JsonPrimitive(book.getName()));
        new Gson().toJson(jsonObject, resp.getWriter());
    }

    /** 处理删除商品项的请求 */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        String userKey = "user";
        if (session != null && session.getAttribute(userKey) != null) {
            int itemId = Integer.parseInt(req.getParameter("itemId"));
            Cart cart = (Cart) session.getAttribute("cart");
            Map<Integer, CartItem> items = cart.getItems();
            items.remove(itemId);
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /** 处理清空购物车的请求 */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        String userKey = "user";
        if (session != null && session.getAttribute(userKey) != null) {
            Cart cart = (Cart) session.getAttribute("cart");
            cart.clear();
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /** 处理修改商品数量的请求 */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        String userKey = "user";
        if (session != null && session.getAttribute(userKey) != null) {
            int itemId = Integer.parseInt(req.getParameter("itemId"));
            int count = Integer.parseInt(req.getParameter("count"));
            Cart cart = (Cart) session.getAttribute("cart");
            cart.updateCount(itemId, count); //修改商品数量
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }

}
