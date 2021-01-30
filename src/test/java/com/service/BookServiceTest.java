package com.service;

import com.pojo.Book;
import com.pojo.Page;
import com.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description
 * @Author IceCube
 * @Date 2020/12/3 19:52
 */
class BookServiceTest {
    private BookService bookService = new BookServiceImpl();

    @Test
    void addBook() {
        bookService.addBook(new Book(null, "Java程序设计", "OneIce", 100.0, 9999, 100, null));
    }

    @Test
    void deleteBookById() {
    }

    @Test
    void updateBook() {
    }

    @Test
    void queryBookById() {
    }

    @Test
    void queryBooks() {
        List<Book> books = bookService.queryBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    void page() {
        Page<Book> page = bookService.page(2, 4);
        System.out.println(page);
    }
}