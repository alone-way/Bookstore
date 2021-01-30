package com.pojo;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @Description 订单模型, 只存储订单的元数据, 订单项目
 * @Author OneIce
 * @Date 2021/1/21 16:59
 */
public class Order {
    private String orderId; //订单编号
    private Timestamp createTime; //创建时间
    private BigDecimal price; //订单价格
    private Integer status = 0; //订单状态, 0未发货, 1已发货, 2已收货
    private Integer userId; //所属的用户id

    public Order() {
    }

    public Order(String orderId, Timestamp createTime, BigDecimal price, Integer status, Integer userId) {
        this.orderId = orderId;
        this.createTime = createTime;
        this.price = price;
        this.status = status;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", createTime=" + createTime +
                ", price=" + price +
                ", status=" + status +
                ", userId=" + userId +
                '}';
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
