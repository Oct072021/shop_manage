package com.separate.fruit.Dao;

import com.separate.fruit.entity.GoodsType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsTypeDao {
    //添加类型
    boolean insert(GoodsType record);

    //显示分页
    List<GoodsType> selectGoodsType(@Param("num") Integer num, @Param("size") Integer size,
                                    @Param("t") GoodsType t);

    int countType(@Param("t") GoodsType t);

    //删除
    boolean deleteType(Integer id);

    //找到详细信息
    GoodsType FindByIdType(Integer id);

    //修改类型
    boolean updateById(GoodsType goodsType);
}