package com.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author IceCube
 * @date 2020/11/1 18:56
 */
public class JdbcUtils {
    private static DruidDataSource dataSource;
    private static final ThreadLocal<Connection> CONNECTION_THREAD_LOCAL = new ThreadLocal<Connection>() {
        @Override
        protected Connection initialValue() {
            try {
                DruidPooledConnection connection = dataSource.getConnection();
                connection.setAutoCommit(false);
                return connection;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                System.err.println("获取连接失败...");
                return null;
            }
        }
    };

    static {
        Properties properties = new Properties();
        try {
            InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(in);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从数据库连接池获取连接 (默认关闭了自动提交)
     * @return 若返回null, 说明获取连接失败
     */
    public static Connection getConnection() {
        return CONNECTION_THREAD_LOCAL.get();
    }

    /** 提交事务, 关闭连接 */
    public static void commitAndClose() {
        try (Connection connection = CONNECTION_THREAD_LOCAL.get()) {
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //必须移除当前线程的连接, 原因是:
        //Tomcat底层使用了线程池技术, 导致线程被复用, 获取到已经关闭的连接
        //准确地说是获取到已经释放回连接池的连接, 如果使用了这样的连接, 会导致异常抛出
        CONNECTION_THREAD_LOCAL.remove();
    }

    /** 回滚事务, 关闭连接 */
    public static void rollbackAndClose() {
        try (Connection connection = CONNECTION_THREAD_LOCAL.get()) {
            connection.rollback();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //移除当前线程的连接
        CONNECTION_THREAD_LOCAL.remove();
    }

}
