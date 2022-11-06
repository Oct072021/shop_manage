package com.separate.fruit.cotroller;

import com.separate.fruit.entity.GoodsType;
import com.separate.fruit.entity.utils.Page;
import com.separate.fruit.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GoodsTypeController {
    @Autowired
    private GoodsTypeService goodsTypeService;

    @RequestMapping("TypeAll/{page}/{size}")
    public Page<GoodsType> selectTypeAll(@PathVariable("page") Integer page,
                                         @PathVariable("size") Integer size, @RequestBody GoodsType goodsType) {
        Integer num = page * size;
        return this.goodsTypeService.selectGoodsType(num, size, goodsType);
    }

    @PutMapping("addType")
    public boolean addType(@RequestBody GoodsType goodsType) {
        return this.goodsTypeService.insert(goodsType);
    }

    @DeleteMapping("deleteType")
    public boolean deleteType(Integer id) {
        return this.goodsTypeService.deleteType(id);
    }

    @RequestMapping("findByIdType")
    public GoodsType findByIdType(Integer id) {
        return this.goodsTypeService.FindByIdType(id);
    }

    @PutMapping("updateType")
    public boolean updateType(@RequestBody GoodsType goodsType) {
        return this.goodsTypeService.updateById(goodsType);
    }
}
