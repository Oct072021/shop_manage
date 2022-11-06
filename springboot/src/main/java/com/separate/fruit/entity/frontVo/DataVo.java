package com.separate.fruit.entity.frontVo;

import java.util.Map;

public class DataVo {

    private Integer value;
    private Map<String, String> itemStyle;

    public Integer getValue() {
        return value;
    }

    public Map<String, String> getItemStyle() {
        return itemStyle;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setItemStyle(Map<String, String> itemStyle) {
        this.itemStyle = itemStyle;
    }
}
