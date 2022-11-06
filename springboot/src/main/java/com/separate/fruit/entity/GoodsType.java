package com.separate.fruit.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * goods_type
 *
 * @author
 */
@Data
public class GoodsType implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer goodsTypeId;//商品类型的id,跟商品进行关联
    private String goodsTypeName;//商品类型的名字

    public Integer getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(Integer goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    public String getGoodsTypeName() {
        return goodsTypeName;
    }

    public void setGoodsTypeName(String goodsTypeName) {
        this.goodsTypeName = goodsTypeName;
    }
}