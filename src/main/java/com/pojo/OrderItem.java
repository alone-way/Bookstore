package com.pojo;

import java.math.BigDecimal;

/**
 * @Description 订单项模型
 * @Author OneIce
 * @Date 2021/1/21 17:06
 */
public class OrderItem {
    private Integer id; //订单项id(主键)
    private String name; //商品名称
    private Integer count; //商品个数
    private BigDecimal price; //商品单价
    private BigDecimal totalPrice; //商品总价
    private String orderId; //所属的订单编号

    public OrderItem() {
        updateTotalPrice();
    }

    public OrderItem(Integer id, String name, Integer count, BigDecimal price, String orderId) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.orderId = orderId;
        updateTotalPrice();
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                ", orderId='" + orderId + '\'' +
                '}';
    }

    public void updateTotalPrice() {
        if (price != null && count != null) { //避免空指针异常
            totalPrice = price.multiply(BigDecimal.valueOf(count));
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
        updateTotalPrice();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
        updateTotalPrice();
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
