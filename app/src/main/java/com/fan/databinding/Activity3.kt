package com.fan.databinding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.databinding.DataBindingUtil
import com.fan.databinding.databinding.Activity3LayoutBinding
import com.fan.databinding.model.ObservableGoods

/**
 * 演示 ObservableField 的使用
 */
class Activity3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity3_layout)

        val contentView =
            DataBindingUtil.setContentView<Activity3LayoutBinding>(this, R.layout.activity3_layout)

        val observableGoodsBean = ObservableGoods("BBB", "B detail", 4f)

        contentView.observableGoodsBean = observableGoodsBean


        findViewById<Button>(R.id.modify_name_button).setOnClickListener {
            observableGoodsBean.name.set("点击了,哈哈哈")
        }
        findViewById<Button>(R.id.modify_price_button).setOnClickListener {
            observableGoodsBean.price.set(88.22f)
        }
        findViewById<Button>(R.id.go_to_activity4).setOnClickListener {
            startActivity(Intent(this, Activity4::class.java))
        }
    }
}
