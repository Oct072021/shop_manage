package com.separate.fruit.entity;

import java.io.Serializable;
import java.util.List;

/**
 * shop_cart
 *
 * @author
 */
public class ShopCart implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer shopCartId;//购物车Id
    private Integer userId;//购物车属于某个用户
    private Integer goodsId;//商品Id方便找到商品
    private Integer count;//购物车中要购买商品的数量
    private double shopPrice;//商品价格
    private List<Users> usersList;//用户
    private List<Goods> goodsList;//商品

    public Integer getShopCartId() {
        return shopCartId;
    }

    public void setShopCartId(Integer shopCartId) {
        this.shopCartId = shopCartId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }

    public double getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(double shopPrice) {
        this.shopPrice = shopPrice;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }
}