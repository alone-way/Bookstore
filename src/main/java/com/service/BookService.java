package com.service;

import com.pojo.Book;
import com.pojo.Page;

import java.util.List;

/**
 * @Description
 * @Author IceCube
 * @Date 2020/12/3 19:45
 */
public interface BookService {
    boolean addBook(Book book);

    boolean deleteBookById(Integer id);

    boolean updateBook(Book book);

    Book queryBookById(Integer id);

    List<Book> queryBooks();

    Page<Book> page(int pageNo, int pageSize);


    Page<Book> pageByPrice(int min, int max, int pageNo, int pageSize);
}
