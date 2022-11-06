package com.separate.fruit.cotroller;

import com.separate.fruit.entity.ShopCart;
import com.separate.fruit.entity.utils.Page;
import com.separate.fruit.entity.utils.poUtil;
import com.separate.fruit.service.GoodsService;
import com.separate.fruit.service.ShopCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShopCartController {
    @Autowired
    private ShopCartService shopCartService;
    @Autowired
    private GoodsService goodsService;

    //查询出购物车的数据
    @RequestMapping("/shopCart/{page}/{size}")
    public Page<ShopCart> AllShopLimit(@PathVariable("page") Integer page,
                                       @PathVariable("size") Integer size,
                                       @RequestBody poUtil poUtil) {
        Integer num = page * size;
        return this.shopCartService.AllShopCartLimit(num, size, poUtil);
    }

    //删除购物车的商品信息
    @DeleteMapping("deleteShopCart")
    public boolean deleteShopCart(Integer id) {
        return this.shopCartService.deleteKeyIdShopCart(id);
    }

    //查询出购物车的详细信息,然后修改这条信息
    @RequestMapping("shopCartById")
    public ShopCart shopCartById(Integer id) {
        return this.shopCartService.findShopCartId(id);
    }

    //执行修改
    @PutMapping("updateShopCartId")
    public boolean updateShopCartId(@RequestBody ShopCart shopCart) {
        return this.shopCartService.updateByIdShopCart(shopCart);
    }

    //添加商品信息进入购物车
    @PutMapping("addShop")
    public boolean WhereFindShopList(@RequestBody ShopCart shopCart) {
        double shopPrice =
                this.goodsService.findGoods
                        (shopCart.getGoodsId()).getPrice() * shopCart.getCount();
        shopCart.setShopPrice(shopPrice);
        return this.shopCartService.insertShopCart(shopCart);
    }
}
