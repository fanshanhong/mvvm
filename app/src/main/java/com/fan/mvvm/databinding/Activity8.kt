package com.fan.mvvm.databinding

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.fan.mvvm.model.DeleteNumber
import androidx.databinding.BindingConversion
import com.fan.mvvm.R


/**
 * 演示BindingAdapter
 */
class Activity8 : AppCompatActivity() {


    val deleteNumber = DeleteNumber()

    var imageUrl = "123"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity8_layout)

        val contentView =
            DataBindingUtil.setContentView<Activity8LayoutBinding>(this,
                R.layout.activity8_layout
            )


        contentView.imageUrl = imageUrl

        contentView.deleteNumber = deleteNumber


    }


    fun modifyUrl(view: View) {

        println("------enter modifyUrl:${imageUrl}")

        imageUrl = "456"

    }

    fun addDeleteNumber(view: View) {
        deleteNumber.number++

    }

    fun subDeleteNumber(view: View) {

        if (deleteNumber.number > 0)
            deleteNumber.number--
    }


}

@BindingAdapter("url")
fun loadImage(view: ImageView, url: String) {
    println("loadImage:${url}")
}

@BindingAdapter("android:text")
fun setText(view: Button, text: String) {
    view.setText(text + "-Button")
}

//单个属性
@BindingAdapter("app:hideIfZero")
fun hideIfZero(view: View, number: Int) {
    println("------enter hideIfZero:${number}")
    view.visibility = if (number == 0) View.GONE else View.VISIBLE
}


// 先注释, 否则项目中所有的都会应用这个规则
//@BindingConversion
//fun conversionString(text: String): String {
//    return "$text-conversionString"
//}


// android:background 会自动找到这个方法
// 输入参数是  String, 返回值为 Drawable. 应该就是根据输入参数和返回值来决定找到哪个方法的. 与方法名字无关. 我把名字改成 xx1 试试
@BindingConversion
fun convertStringToDrawable(str: String): Drawable {
    if (str.equals("红色")) {
        return ColorDrawable(Color.parseColor("#FF4081"));
    }
    if (str.equals("蓝色")) {
        return ColorDrawable(Color.parseColor("#3F51B5"));
    }
    return ColorDrawable(Color.parseColor("#344567"));
}

// android:textColor 会自动找到这个方法
// 输入参数是  String, 返回值为 Int 型指示的颜色. 应该就是根据输入参数和返回值来决定找到哪个方法的
@BindingConversion
fun convertStringToColor(str: String): Int {
    if (str.equals("红色")) {
        return Color.parseColor("#FF4081");
    }
    if (str.equals("蓝色")) {
        return Color.parseColor("#3F51B5");
    }
    return Color.parseColor("#344567");
}


