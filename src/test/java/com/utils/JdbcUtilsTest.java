package com.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

/**
 * @author IceCube
 * @date 2020/11/1 19:19
 */
public class JdbcUtilsTest {
    @Test
    public void testConnection() {
        for (int i = 0; i < 100; i++) {
            Connection conn = JdbcUtils.getConnection();
            Assertions.assertNotNull(conn);
        }
    }
}
