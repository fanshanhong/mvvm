package com.fan.databinding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.fan.databinding.databinding.MyCustomData
import com.fan.databinding.model.User

/**
 * 演示 Data Binding 基础使用
 */
class Activity1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)


        // 返回的类型就是 MyCustomData
        // 如果不指定 class, 返回的类型是 ActivityMain + Binding
        val contentView =
            DataBindingUtil.setContentView<MyCustomData>(this, R.layout.activity1_layout)
        val userInfo = User()
        userInfo.name = "fanfan"
        userInfo.age = 122
        contentView.userInfo = userInfo


        findViewById<View>(R.id.changeAgeButton).setOnClickListener {
            // 直接修改view的 text 属性来修改
            contentView.ageView.text = "8889"

            // 下面这样也行
            // contentView.ageView.setText("11")
        }


        findViewById<View>(R.id.changeNameButton).setOnClickListener {
            // 修改数据源,直接就能修改view展示的内容了
            userInfo.name = "my new name"
            userInfo.age = 16

            // 最后必须给  ViewBinding 再 set 一下数据, 否则并不会让 UI 自动更新
            contentView.userInfo = userInfo
        }

        findViewById<View>(R.id.go_to_second_activity).setOnClickListener {
            startActivity(Intent(this, Activity2::class.java))
        }
    }
}
