package com.separate.fruit.service;

import com.separate.fruit.Dao.OrderDao;
import com.separate.fruit.entity.Order;
import com.separate.fruit.entity.utils.Page;
import com.separate.fruit.entity.utils.poUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    //查询出所有信息
    @Transactional
    public Page<Order> AllOrderLimit(Integer num, Integer size, poUtil poUtil) {
        Page<Order> page = new Page<>();
        page.setList(this.orderDao.listOrdersAll(num, size, poUtil));
        page.setNum(this.orderDao.countOrder(poUtil));
        return page;
    }

    //删除订单
    @Transactional
    public boolean DeleteOrder(Integer id) {
        return this.orderDao.DeleteOrder(id);
    }

    //查询出订单的信息,然后对订单进行修改
    @Transactional
    public Order orderById(Integer id) {
        return this.orderDao.orderById(id);
    }

    //修改订单信息
    @Transactional
    public boolean updateOrderById(Order order) {
        return this.orderDao.updateOrderById(order);
    }

    //添加订单
    public boolean OrderAdd(Order order) {
        return this.orderDao.OrderAdd(order);
    }
}
