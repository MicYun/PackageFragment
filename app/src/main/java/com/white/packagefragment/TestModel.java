package com.white.packagefragment;

import com.white.runfragment.model.BaseModel;

import java.util.List;

public class TestModel implements BaseModel<String>{

    private List<String> data;

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    @Override
    public List<String> getList() {
        return data;
    }
}
