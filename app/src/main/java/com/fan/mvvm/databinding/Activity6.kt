package com.fan.mvvm.databinding

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.fan.mvvm.R
import com.fan.mvvm.model.User

/**
 * 演示事件绑定
 *
 * 严格意义上来说，事件绑定也是一种变量绑定，只不过设置的变量是回调接口而已 事件绑定可用于以下多种回调事件
 */
class Activity6 : AppCompatActivity() {



    var contentView: Activity6LayoutBinding? = null

    var user: User? = null

    var mycontext : Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity6_layout)

        contentView =
            DataBindingUtil.setContentView<Activity6LayoutBinding>(this,
                R.layout.activity6_layout
            )

        user = User()
        user?.name = "HHhh"

        contentView?.userInfo = user
        contentView?.userPresenter = UserPresenter()

        mycontext = this


    }


    inner class UserPresenter {
        fun onUserNameClick(context: Context, user: User) {
            println("onUserNameClick: ${"用户名：" + user.name}")
            Toast.makeText(context, "用户名${user.name}", Toast.LENGTH_LONG).show()

            val  a = Activity6@this
            println("------ac:${a}")                  // com.fan.databinding.Activity6$UserPresenter@55ff8a4
            println("------this:${this}")                  // com.fan.databinding.Activity6$UserPresenter@55ff8a4
            println("------context:${context}")       // com.fan.databinding.Activity6@2e35dfe
            println("------mycontext:${mycontext}")   // com.fan.databinding.Activity6@2e35dfe
            println("------outer@this:${this@Activity6}")// com.fan.databinding.Activity6@2e35dfe

        }

        fun onooo(view : View) {
            println("------11111111")
        }

        fun afterTextChanged(s: Editable) {
            user?.name = s.toString()
            println("afterTextChanged: ${s.toString()}")

            // 这里 给user 的属性赋值,仅仅是修改数据, 不会修改 TextView的UI显示
            // 如果想要同时更新UI, 需要使用 Observable Field 或者 将 User的 name 设置成 @Bindable, 如下
            // @Bindable
            // var name: String? = null
            // set(value) {
            //     field = value
            //     notifyPropertyChanged(BR.name)
            // }
        }

    }
}
