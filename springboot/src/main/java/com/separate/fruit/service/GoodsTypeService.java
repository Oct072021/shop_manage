package com.separate.fruit.service;

import com.separate.fruit.Dao.GoodsTypeDao;
import com.separate.fruit.entity.GoodsType;
import com.separate.fruit.entity.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GoodsTypeService {

    @Autowired
    private GoodsTypeDao goodsTypeDao;

    @Transactional
    public Page<GoodsType> selectGoodsType(Integer num, Integer size, GoodsType goodsType) {
        Page<GoodsType> page = new Page<GoodsType>();
        page.setList(this.goodsTypeDao.selectGoodsType(num, size, goodsType));
        page.setNum(this.goodsTypeDao.countType(goodsType));
        return page;
    }

    @Transactional
    public boolean insert(GoodsType record) {
        return this.goodsTypeDao.insert(record);
    }

    @Transactional
    public boolean deleteType(Integer id) {
        return this.goodsTypeDao.deleteType(id);
    }

    @Transactional
    public GoodsType FindByIdType(Integer id) {
        return this.goodsTypeDao.FindByIdType(id);
    }

    @Transactional
    public boolean updateById(GoodsType goodsType) {
        return this.goodsTypeDao.updateById(goodsType);
    }
}
