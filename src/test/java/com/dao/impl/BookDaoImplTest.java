package com.dao.impl;

import com.dao.BookDao;
import com.pojo.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

/**
 * @Description
 * @Author IceCube
 * @Date 2020/12/3 19:21
 */
class BookDaoImplTest {
    private BookDao bookDao = new BookDaoImpl();

    @Test
    void addBook() {
        bookDao.addBook(new Book(null, "Java程序设计", "OneIce", 100.0, 9999, 100, null));
    }

    @Test
    void deleteBookById() {
        bookDao.deleteBookById(21);
    }

    @Test
    void updateBook() {
        bookDao.updateBook(new Book(21, "Java程序设计", "OneIce", 140.23, 9999, 100, null));
    }

    @Test
    void queryBookById() {
        Book book = bookDao.queryBookById(21);
        System.out.println(book);
    }

    @Test
    void queryBooks() {
        List<Book> books = bookDao.queryBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    void queryForPageTotalCount() {
        int pageTotalCount = bookDao.queryForPageTotalCountByPrice();
        System.out.println(pageTotalCount);
    }

    @Test
    void queryForItems() {
        List<Book> books = bookDao.queryForItemsByPrice(2, 4);
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @ParameterizedTest
    @CsvSource({"0,1000", "0,20"})
    public void queryForPageTotalCount(int min, int max) {
        int pageTotalCount = bookDao.queryForPageTotalCountByPrice(min, max);
        System.out.println(pageTotalCount);
    }

    @ParameterizedTest
    @CsvSource({"0,20,0,2", "0,20,2,2"})
    public void queryForItems(int min, int max, int begin, int pageSize) {
        List<Book> books = bookDao.queryForItemsByPrice(min, max, begin, pageSize);
        for (Book book : books) {
            System.out.println(book);
        }
    }


}