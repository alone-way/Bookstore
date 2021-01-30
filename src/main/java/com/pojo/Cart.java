package com.pojo;

import com.google.gson.JsonElement;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 购物车模型
 * @Author OneIce
 * @Date 2021/1/20 15:56
 */
public class Cart {
    private Integer totalCount = 0; //总商品个数
    private BigDecimal totalPrice = new BigDecimal(0); //总商品价格
    private Map<Integer, CartItem> items = new HashMap<>(); //购物车中的商品, key是商品编号

    /** 添加商品项 */
    public void addItem(CartItem cartItem) {
        Integer id = cartItem.getId();
        CartItem item = items.get(id);
        if (item != null) { //商品已存在
            item.setCount(item.getCount() + cartItem.getCount());
        } else { //商品不存在
            items.put(cartItem.getId(), cartItem);
        }
        totalCount += cartItem.getCount();
        totalPrice = totalPrice.add(cartItem.getTotalPrice());
    }

    /** 修改指定商品数量 */
    public void updateCount(Integer id, Integer count) {
        CartItem item = items.get(id);
        int increment = count - item.getCount();
        this.totalCount = this.totalCount + increment;
        this.totalPrice = this.totalPrice.add(item.getPrice().multiply(new BigDecimal(increment)));
        item.setCount(count);
    }

    /** 删除商品项 */
    public void delete(Integer id) {
        CartItem item = items.get(id);
        totalCount = totalCount - item.getCount();
        totalPrice = totalPrice.subtract(item.getTotalPrice());
        items.remove(id);
    }

    /** 清空购物车 */
    public void clear() {
        totalCount = 0;
        totalPrice = new BigDecimal(0);
        items.clear();
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + totalCount +
                ", totalPrice=" + totalPrice +
                ", items=" + items +
                '}';
    }
}
