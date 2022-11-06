package com.separate.fruit.entity.utils;

import java.io.Serializable;
import java.util.List;


/**
 * 分页的工具持久化类
 *
 * @author hp
 */
public class Page<T> implements Serializable {
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;
    private List<T> list; //接受任意类型
    private Integer num; //分页的数目

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
