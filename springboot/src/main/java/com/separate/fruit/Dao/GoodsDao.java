package com.separate.fruit.Dao;

import com.separate.fruit.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsDao {
    //查询数据
    List<Goods> AllGoodsLimit(@Param("num") int num, @Param("size") int size, @Param("goods") Goods goods);

    //查询的数据一共有多少条
    int countGoods(@Param("goods") Goods goods);

    //删除数据
    boolean deleteKeyIdGoods(@Param("goodsId") Integer goodsId);

    //找到需要更新的数据
    Goods findGoodsId(@Param("goodsId") Integer goodsId);

    //找到需要更新的数据后向数据库执行更新语句
    boolean updateGoods(Goods goods);

    // 添加数据
    boolean insertGoods(Goods goods);

    //传商品名称和商品Id
    List<Goods> whereGoods();
}