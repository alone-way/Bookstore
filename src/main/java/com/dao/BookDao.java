package com.dao;

import com.pojo.Book;

import java.util.List;

/**
 * @Description
 * @Author IceCube
 * @Date 2020/12/3 19:03
 */
public interface BookDao {
    int addBook(Book book) ;

    int deleteBookById(Integer id) ;

    int updateBook(Book book) ;

    Book queryBookById(Integer id) ;

    List<Book> queryBooks();

    int queryForPageTotalCountByPrice() ;

    List<Book> queryForItemsByPrice(int begin, int pageSize) ;

    int queryForPageTotalCountByPrice(int min, int max) ;

    List<Book> queryForItemsByPrice(int min, int max, int begin, int pageSize) ;
}
