package com.fan.databinding.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;


/**
 * @Description: Java 编写的实体类
 * @Author: fan
 * @Date: 2021-02-07 16:14
 * @Modify:
 */
public class GoodsJava extends BaseObservable {

    @Bindable
    private String name;
    @Bindable
    private float price;
    @Bindable
    private String detail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("GoodsJava.setName:" + name);
        this.name = name;

        // 通知UI,如果使用了 BR.price, 就更新
        notifyPropertyChanged(BR.name);
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        System.out.println("GoodsJava.setPrice:" + price);
        this.price = price;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {

        name = "JavaGoods的name也变化了!!!";
        this.detail = detail;

        // 通知所有字段.
        // 通知UI, 如果使用了@Bindable的字段, 都刷新
        notifyChange();
    }
}
