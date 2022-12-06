package com.separate.fruit.cotroller;

import com.separate.fruit.entity.Goods;
import com.separate.fruit.entity.utils.Page;
import com.separate.fruit.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    //查询数据
    @RequestMapping("/goods/{page}/{size}")
    public Page<Goods> AllGoodsLimit(@PathVariable("page") Integer page,
                                     @PathVariable("size") Integer size,
                                     @RequestBody Goods goods) {

        int num = page * size;
        return this.goodsService.AllGoodsLimit(num, size, goods);
    }

    /**
     * 删除商品数据
     *
     * @param id
     * @return
     */
    @DeleteMapping("/deleteGoods")
    public boolean deleteKeyGoodsById(Integer id) {
        return this.goodsService.deleteKeyIdGoods(id);
    }

    //查询出数据,然后修改商品数据
    @RequestMapping("/findGoods")
    public Goods findGoodsById(Integer id) {
        return this.goodsService.findGoods(id);
    }

    //修改商品数据
    @PutMapping("/updateGoods")
    public boolean updateGoodsById(@RequestBody Goods goods) {
        return this.goodsService.updateGoods(goods);
    }

    //添加商品
    @PutMapping("/addGoods")
    public boolean insertGoods(@RequestBody Goods goods) {
        if (goods.getCount() > 0) {
            goods.setOnSale("yes");
        } else {
            goods.setOnSale("no");
        }
        goods.setGoodsImg("/images/front/nurse/xifas.jpg");
        return this.goodsService.insertGoods(goods);
    }

    //订单和购物车的商品处理
    @GetMapping("whereGoods")
    public List<Goods> whereGoodsAll() {
        return this.goodsService.whereGoodsAll();
    }
}
