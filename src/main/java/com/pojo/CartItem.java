package com.pojo;

import java.math.BigDecimal;

/**
 * @Description 购物车的商品项
 * @Author OneIce
 * @Date 2021/1/20 15:51
 */
public class CartItem {
    private Integer id; //商品编号
    private String name; //商品名称
    private Integer count; //商品数量
    private BigDecimal price; //商品单价
    private BigDecimal totalPrice; //商品总价

    public CartItem() {
    }

    public CartItem(Integer id, String name, Integer count, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        updateTotalPrice();
    }


    private void updateTotalPrice() {
        if (this.price != null && this.count != null) { //避免空指针异常
            this.totalPrice = this.price.multiply(BigDecimal.valueOf(this.count));
        }
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


    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                '}';
    }

}
