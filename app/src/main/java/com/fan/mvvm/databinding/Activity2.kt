package com.fan.mvvm.databinding

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import com.fan.mvvm.BR
import com.fan.mvvm.R
import com.fan.mvvm.model.Goods
import com.fan.mvvm.model.GoodsJava

/**
 * @Description: 演示 Kotlin 和 Java 编写的实体类中 BaseObservable 的使用
 * @Author: fan
 * @Date: 2021-02-07 15:11
 * @Modify:
 */
class Activity2 : AppCompatActivity() {

    val TAG: String = "SecActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // activity_sec_layout 的 <data>没指定 class, 因此, contentView 的 类型是 ActivitySecLayoutBinding
        val contentView = DataBindingUtil.setContentView<Activity2LayoutBinding>(
            this,
            R.layout.activity2_layout
        )

        var goods = Goods()

        goods.name = "new goods"

        contentView.goods = goods


        findViewById<View>(R.id.modify_goods_name).setOnClickListener {

            // 调用setPrice()给price 赋值
            goods.price = 2.22f
            // 调用了 setName()方法, 其中调用了 notifyPropertyChanged(BR.name) , 因此, 使用到了 BR.name的UI会及时更新.
            // 但是 price 是不会变的.
            goods.name = "我是Goods的名字"
        }
        findViewById<View>(R.id.modify_goods_price).setOnClickListener {
            // 同上面的一样,  因为 setPrice()方法中没有调用 notify() 方法, 因此 price 不会变的.
            goods.price = 3.33f
        }
        findViewById<View>(R.id.modify_goods_all).setOnClickListener {
            goods.name = "name也变化了2222"
            goods.price = 1101.1f

            // setDetail中使用了 notifyChange(), 因此使用了该对象的字段的的UI都会及时更新(包括那些没有使用@Bindable 的字段.都会更新的)
            goods.detail = "新的detail"
        }


        // --- java goods ----

        var goodsJava = GoodsJava()

//        goodsJava.name = "java goods name"

        goodsJava.setName("去去去")

        // 其实这一句不写也可以的.   setName() 方法会通知 UI更新.. 好像不是这样的. 好像必须写呀. 这一句是必须的, 否则关联不上的.
        contentView.goodsJava = goodsJava


        findViewById<View>(R.id.modify_java_goods_name).setOnClickListener {


            // 对于 Java 编写的 model 类, 要注意:
            // 如果 price 是 public 的, 直接这样 = 号 赋值, 是不会调用它的 setPrices() 方法的.
            // 如果 price 是 private的, 这样直接 = 号赋值, 是会自动调用 setPrice() 方法的

            goodsJava.price = 2.22f // 这里, setPrice() 中没有 notify 方法, 因此UI不更新


            // setName()方法, 其中调用了 notifyPropertyChanged(BR.name) , 因此, 使用到了 BR.name的UI会及时更新.
            // 但是 price 是不会变的.
            goodsJava.name = "我是JavaGoods的名字哈哈哈"

        }
        findViewById<View>(R.id.modify_java_goods_price).setOnClickListener {
            // 同上面的一样,  因为 setPrice()方法中没有调用 notify() 方法, 因此 price 不会变的.
            goodsJava.price = 3.33f
        }
        findViewById<View>(R.id.modify_java_goods_all).setOnClickListener {
            goodsJava.price = 111.1f

            // setDetail中使用了 notifyChange(), 因此使用了该对象的字段的的UI都会及时更新
            goodsJava.detail = "JavaGoods的新的detail"
        }


        // 实现了 Observable 接口的类允许注册一个监听器，当可观察对象的属性更改时就会通知这个监听器，此时就需要用到 OnPropertyChangedCallback
        //
        //当中 propertyId 就用于标识特定的字段
        goods.addOnPropertyChangedCallback(
            object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {

                    if (propertyId == BR.name) {
                        Log.e(TAG, "BR.name")

                    } else if (propertyId == BR.detail) {
                        Log.e(TAG, "BR.detail")
                    } else if (propertyId == BR._all) {
                        Log.e(TAG, "BR._all")
                    } else {
                        Log.e(TAG, "未知")
                    }

                    when (propertyId) {
                        BR.name -> Log.e(TAG, "BR.name")
                        BR.detail -> Log.e(TAG, "BR.detail")
                        BR._all -> Log.e(TAG, "BR._all")
                    }

                }

            }
        )


        findViewById<Button>(R.id.go_to_activity3).setOnClickListener {
            startActivity(Intent(this, Activity3::class.java))
        }
    }
}