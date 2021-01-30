package com.service.impl;

import com.dao.BookDao;
import com.dao.impl.BookDaoImpl;
import com.pojo.Book;
import com.pojo.Page;
import com.service.BookService;

import java.util.List;

/**
 * @Description
 * @Author IceCube
 * @Date 2020/12/3 19:47
 */
public class BookServiceImpl implements BookService {
    private final BookDao bookDao = new BookDaoImpl();

    @Override
    public boolean addBook(Book book) {
        return bookDao.addBook(book) != 0;
    }

    @Override
    public boolean deleteBookById(Integer id) {
        return bookDao.deleteBookById(id) != 0;
    }

    @Override
    public boolean updateBook(Book book) {
        return bookDao.updateBook(book) != 0;
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    /** 根据pageNo和pageSize创建Page对象 */
    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<>();
        //1.设置总记录数
        int pageTotalCount = bookDao.queryForPageTotalCountByPrice();
        page.setPageTotalCount(pageTotalCount);
        //2.设置页大小
        page.setPageSize(pageSize);
        //3.设置总页数
        int pageTotal = (int) Math.ceil(pageTotalCount * 1.0 / page.getPageSize());
        page.setPageTotal(pageTotal);
        //3.设置页码
        page.setPageNo(pageNo);
        //4.求当前页数据
        int begin = (page.getPageNo() - 1) * page.getPageSize(); //当前页数据的起始索引
        List<Book> books = bookDao.queryForItemsByPrice(begin, page.getPageSize());
        page.setItems(books);
        return page;
    }

    /**
     * 搜索指定价格区间, 页码, 页大小下的图书
     *
     * @param min      最低价格
     * @param max      最高价格
     * @param pageNo   页码
     * @param pageSize 页大小
     * @return 返回包含图书数据和分页信息的Page对象
     */
    @Override
    public Page<Book> pageByPrice(int min, int max, int pageNo, int pageSize) {
        Page<Book> page = new Page<>();
        //设置总记录数
        int pageTotalCount = bookDao.queryForPageTotalCountByPrice(min, max);
        page.setPageTotalCount(pageTotalCount);
        //设置页大小
        page.setPageSize(pageSize);
        //设置总页数
        int pageTotal = (int) Math.ceil(pageTotalCount * 1.0 / page.getPageSize());
        page.setPageTotal(pageTotal);
        //设置页码
        page.setPageNo(pageNo);
        //设置图书数据
        int begin = (page.getPageNo() - 1) * page.getPageSize();
        List<Book> items = bookDao.queryForItemsByPrice(min, max, begin, page.getPageSize());
        page.setItems(items);
        return page;
    }
}
