package com.separate.fruit.Dao;

import com.separate.fruit.entity.ShopCart;
import com.separate.fruit.entity.utils.poUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShopCartDao {
    //查询购物车信息
    List<ShopCart> AllShopCartLimit(@Param("num") Integer num, @Param("size") Integer size,
                                    @Param("shop") poUtil shopCart);

    //查询出的信息一共有多少条
    int countShopCart(@Param("shop") poUtil shopCart);

    //删除数据
    boolean deleteKeyIdShopCart(Integer shopcartId);

    //找到数据的详细信息
    ShopCart findShopCartId(Integer shopcartId);

    //修改数据
    boolean updateShopCartById(ShopCart shopCart);

    //添加数据
    boolean insertShopCart(ShopCart shopCart);
}