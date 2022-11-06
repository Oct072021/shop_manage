package com.separate.fruit.service;

import com.separate.fruit.Dao.GoodsDao;
import com.separate.fruit.entity.Goods;
import com.separate.fruit.entity.utils.Page;
import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    //查询数据
    @Transactional
    public Page<Goods> AllGoodsLimit(int num, int size, Goods goods) {
        Page<Goods> page = new Page<>();
        page.setList(this.goodsDao.AllGoodsLimit(num, size, goods));
        page.setNum(this.goodsDao.countGoods(goods));
        return page;
    }

    //删除数据
    @Transactional
    public boolean deleteKeyIdGoods(Integer goodsId) {
        return this.goodsDao.deleteKeyIdGoods(goodsId);
    }

    //查询出数据方便修改
    @Transactional
    public Goods findGoods(Integer goodsId) {
        return this.goodsDao.findGoodsId(goodsId);
    }

    //修改数据
    @Transactional
    public boolean updateGoods(Goods goods) {
        return this.goodsDao.updateGoods(goods);
    }

    //添加数据
    @Transactional
    public boolean insertGoods(Goods goods) {
        return this.goodsDao.insertGoods(goods);
    }

    //后台方便订单和购物车添加商品
    @Transactional
    public List<Goods> whereGoodsAll() {
        return this.goodsDao.whereGoods();
    }
}
