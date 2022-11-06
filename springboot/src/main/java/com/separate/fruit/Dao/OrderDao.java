package com.separate.fruit.Dao;

import com.separate.fruit.entity.Order;
import com.separate.fruit.entity.utils.poUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderDao {
    //查询出订单数据
    List<Order> listOrdersAll(@Param("num") Integer num, @Param("size") Integer size,
                              @Param("shop") poUtil shopUtil);

    //数据库中的数量
    int countOrder(@Param("shop") poUtil shopUtil);

    //添加订单数据
    boolean OrderAdd(Order order);

    //删除订单
    boolean DeleteOrder(Integer id);

    //找到对应数据
    Order orderById(Integer id);

    //更改订单表中的数据
    boolean updateOrderById(Order order);
}
