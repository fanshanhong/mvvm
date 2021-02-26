package com.fan.mvvm.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.fan.mvvm.R
import com.fan.mvvm.model.ObservableGoods
import com.fan.mvvm.model.Goods


/**
 * 演示双向数据绑定
 *
 * 双向绑定的意思即为当数据改变时同时使视图刷新，而视图改变时也可以同时改变数据
 */
class Activity5 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity5_layout)


        val contentView =
            DataBindingUtil.setContentView<Activity5LayoutBinding>(this,
                R.layout.activity5_layout
            );

        val goods = Goods()
        goods.name ="goodsName"
        contentView.goods = goods

        val observableGoods = ObservableGoods("observableGoodsName", "hi", 23f)
        contentView.observableGoods = observableGoods
    }
}
