package com.dao.impl;

import com.dao.BookDao;
import com.pojo.Book;

import java.util.List;

/**
 * @Description
 * @Author IceCube
 * @Date 2020/12/3 19:06
 */
public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book(`name`,`author`,`price`,`sales`,`stock`,`img_path`) values(?,?,?,?,?,?)";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql = "delete from t_book where `id` = ?";
        return update(sql, id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set `name`=?,`author`=?,`price`=?,`sales`=?,`stock`=?,`img_path`=? where id=?";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath(), book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` from t_book where `id`=?";
        return queryForOne(sql, Book.class, id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` from t_book";
        return queryForList(sql, Book.class);
    }


    /** 查询总记录数(图书总数量) */
    @Override
    public int queryForPageTotalCountByPrice() {
        String sql = "select count(*) from t_book";
        //因为queryForSingleValue()方法返回的是Long类型
        //需要先转为String, 通过String才能转为Integer
        Object obj = queryForSingleValue(sql);
        return Integer.parseInt(String.valueOf(obj));
    }

    /**
     * 查询指定起始位置, 指定数量下的图书记录
     *
     * @param begin    起始索引位置
     * @param pageSize 要查询的记录数
     * @return 图书集合
     */
    @Override
    public List<Book> queryForItemsByPrice(int begin, int pageSize) {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` from t_book limit ?,?";
        List<Book> books = queryForList(sql, Book.class, begin, pageSize);
        return books;
    }

    /** 查询指定价格区间下的图书总数量 */
    @Override
    public int queryForPageTotalCountByPrice(int min, int max) {
        String sql = "select count(*) from t_book tb_book where price between ? and ?";
        int pageTotalCount = Integer.parseInt(String.valueOf(queryForSingleValue(sql, min, max)));
        return pageTotalCount;
    }

    /** 查询指定价格区间, 起始索引, 记录数下的图书数据 */
    @Override
    public List<Book> queryForItemsByPrice(int min, int max, int begin, int pageSize) {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` from t_book " +
                "where price between ? and ? order by price limit ?,?";
        List<Book> books = queryForList(sql, Book.class, min, max, begin, pageSize);
        return books;
    }
}
