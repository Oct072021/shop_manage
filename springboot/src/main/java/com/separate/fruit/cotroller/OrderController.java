package com.separate.fruit.cotroller;

import com.separate.fruit.entity.Goods;
import com.separate.fruit.entity.Order;
import com.separate.fruit.entity.utils.Page;
import com.separate.fruit.entity.utils.poUtil;
import com.separate.fruit.service.GoodsService;
import com.separate.fruit.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private GoodsService goodsService;

    //查询订单
    @RequestMapping("findOrder/{page}/{size}")
    public Page<Order> findOrder(@PathVariable("page") Integer page,
                                 @PathVariable("size") Integer size, @RequestBody poUtil shopUtil) {
        Integer num = page * size;
        return this.orderService.AllOrderLimit(num, size, shopUtil);
    }

    //删除订单
    @DeleteMapping("deleteOrder")
    public boolean deleteOrder(Integer id) {
        return this.orderService.DeleteOrder(id);
    }


    //查询到订单详细信息,然后进行修改
    @RequestMapping("OrderById")
    public Order OrderById(Integer id) {
        return this.orderService.orderById(id);
    }

    //修改订单数据
    @PutMapping("updateOrderById")
    public boolean updateOrder(@RequestBody Order order) {
        return this.orderService.updateOrderById(order);
    }

    //添加订单
    @PutMapping("addOrder")
    public boolean AddOrder(@RequestBody Order order) {
        Goods goods = this.goodsService.findGoods(order.getGoodsId());
        double price = goods.getPrice() * order.getOrderCount();
        order.setOrderPrice(price);
        return this.orderService.OrderAdd(order);
    }
}
