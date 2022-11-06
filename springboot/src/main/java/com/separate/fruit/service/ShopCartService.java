package com.separate.fruit.service;

import com.separate.fruit.Dao.ShopCartDao;
import com.separate.fruit.entity.ShopCart;
import com.separate.fruit.entity.utils.Page;
import com.separate.fruit.entity.utils.poUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShopCartService {

    @Autowired
    private ShopCartDao shopCartDao;

    //查询数据
    @Transactional
    public Page<ShopCart> AllShopCartLimit(Integer num, Integer size, poUtil shopCart) {
        Page<ShopCart> page = new Page<>();
        page.setList(this.shopCartDao.AllShopCartLimit(num, size, shopCart));
        page.setNum(this.shopCartDao.countShopCart(shopCart));
        return page;
    }

    //删除数据
    @Transactional
    public boolean deleteKeyIdShopCart(Integer shopcartId) {
        return this.shopCartDao.deleteKeyIdShopCart(shopcartId);
    }

    //寻找数据的详细信息,方便修改
    @Transactional
    public ShopCart findShopCartId(Integer shopcartId) {
        return this.shopCartDao.findShopCartId(shopcartId);
    }

    //修改数据
    @Transactional
    public boolean updateByIdShopCart(ShopCart shopCart) {
        return this.shopCartDao.updateShopCartById(shopCart);
    }

    //添加购物车数据
    @Transactional
    public boolean insertShopCart(ShopCart shopCart) {
        return this.shopCartDao.insertShopCart(shopCart);
    }
}
