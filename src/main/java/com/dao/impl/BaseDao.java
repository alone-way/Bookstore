package com.dao.impl;

import com.utils.JdbcUtils;
import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * @author IceCube
 * @date 2020/11/1 19:26
 */
public abstract class BaseDao {
    //Dbutils的执行器, 类似于Statement
    private QueryRunner queryRunner = new QueryRunner();
    //行处理器: 将结果集的每一行转换成Bean对象
    //(GenerousBeanProcessor的匹配规则: 忽略下划线、不区分大小写, 在此基础上来匹配列名和Bean属性名)
    private RowProcessor rowProcessor = new BasicRowProcessor(new GenerousBeanProcessor());

    public BaseDao() {
    }

    public BaseDao(RowProcessor rowProcessor) {
        this.rowProcessor = rowProcessor;
    }

    /**
     * 执行增删改语句, Insert/Delete/Update
     * @return 受影响的行数
     */
    public int update(String sql, Object... params) {
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.update(conn, sql, params);
        } catch (SQLException throwables) {
            throw new RuntimeException("BaseDao#update()执行错误, sql: " + sql + "params: " + Arrays.toString(params),
                    throwables);
        }
    }


    /**
     * 执行查询语句, 返回结果集的第一行
     * @param c 结果集中每行对应的Java类型
     * @return 查询结果的第一行, 查不到返回null
     */
    public <T> T queryForOne(String sql, Class<T> c, Object... params) {
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new BeanHandler<>(c, rowProcessor), params);
        } catch (SQLException throwables) {
            throw new RuntimeException("BaseDao#queryForOne()执行错误, sql: " + sql + "params: " + Arrays.toString(params),
                    throwables);
        }
    }

    /**
     * 执行查询语句, 将查询结果包装成List并返回
     * @param c 结果集中每行对应的Java类型
     * @return 包含查询结果的List
     */
    public <T> List<T> queryForList(String sql, Class<T> c, Object... params) {
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new BeanListHandler<>(c, rowProcessor), params);
        } catch (SQLException throwables) {
            throw new RuntimeException("BaseDao#queryForList()执行错误, sql: " + sql + "params: " + Arrays.toString(params),
                    throwables);
        }
    }

    /**
     * 执行查询语句, 只返回结果集的第一行第一列的值
     * @return 第一行第一列的值, 没有值时返回null
     */
    public Object queryForSingleValue(String sql, Object... params) {
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new ScalarHandler<>(), params);
        } catch (SQLException throwables) {
            throw new RuntimeException("BaseDao#queryForSingleValue()执行错误, sql: " + sql + "params: " + Arrays.toString(params),
                    throwables);
        }
    }

}
