package com.fan.mvvm.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.fan.mvvm.R
import com.fan.mvvm.model.User

/**
 * 演示 include 和 viewstub
 */
class Activity7 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity7_layout)

        var contentView =
            DataBindingUtil.setContentView<Activity7LayoutBinding>(this,
                R.layout.activity7_layout
            )


        val userInfo = User()
        userInfo.name = "FFF"
        contentView.userInfo = userInfo

    }
}
