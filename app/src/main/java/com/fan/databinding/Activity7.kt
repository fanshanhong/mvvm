package com.fan.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.fan.databinding.databinding.Activity7LayoutBinding
import com.fan.databinding.model.User

/**
 * 演示 include 和 viewstub
 */
class Activity7 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity7_layout)

        var contentView =
            DataBindingUtil.setContentView<Activity7LayoutBinding>(this, R.layout.activity7_layout)


        val userInfo = User()
        userInfo.name = "FFF"
        contentView.userInfo = userInfo

    }
}
