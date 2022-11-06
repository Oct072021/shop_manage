package com.separate.fruit.entity.frontVo;

import java.util.List;

public class BarVo {

    private List<String> names;
    private List<DataVo> values;

    public List<String> getNames() {
        return names;
    }

    public List<DataVo> getValues() {
        return values;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public void setValues(List<DataVo> values) {
        this.values = values;
    }
}
