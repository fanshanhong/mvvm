package com.fan.mvvm.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModelProviders
import com.fan.mvvm.model.UserInfo
import android.widget.Toast
import com.fan.mvvm.R


/**
 * 演示 LiveData
 */
class LiveDataActivity : AppCompatActivity() {

    override fun onStart() {
        super.onStart()
        val myLiveData = ViewModelProviders.of(this).get(MyLiveData::class.java)
        // 其实是调用LiveData的setValue方法, 会触发 Observe的回调方法执行
        myLiveData.stringData.value = "在 onStart 中, stringData被修改了"
    }

    override fun onResume() {
        super.onResume()
        val myLiveData = ViewModelProviders.of(this).get(MyLiveData::class.java)
        // 其实是调用LiveData的setValue方法, 会触发 Observe的回调方法执行
        myLiveData.stringData.value = "在 onResume 中, stringData被修改了"
    }

    override fun onPause() {
        super.onPause()
        val myLiveData = ViewModelProviders.of(this).get(MyLiveData::class.java)
        // 其实是调用LiveData的setValue方法, 会触发 Observe的回调方法执行
        myLiveData.stringData.value = "在 onPause 中, stringData被修改了"
    }

    override fun onStop() {
        super.onStop()
        val myLiveData = ViewModelProviders.of(this).get(MyLiveData::class.java)
        // 其实是调用LiveData的setValue方法, 会触发 Observe的回调方法执行
        myLiveData.stringData.value = "在 onStop 中, stringData被修改了"
    }

    override fun onDestroy() {
        super.onDestroy()
        val myLiveData = ViewModelProviders.of(this).get(MyLiveData::class.java)
        // 其实是调用LiveData的setValue方法, 会触发 Observe的回调方法执行
        myLiveData.stringData.value = "在 onDestroy 中, stringData被修改了"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)


        val nameView = findViewById<TextView>(R.id.name_view)

        val lengthView = findViewById<TextView>(R.id.length_view)

        val myLiveData = ViewModelProviders.of(this).get(MyLiveData::class.java)


        // 注册 stringData 观察, 当 stringData的value 变化的时候, 会回调

        // observe 能观察到 [onStart, onPause] 之间的数据变化, 即:onStart onCreate onResume onPause 中的数据变化
        myLiveData.stringData.observe(this, Observer { println("观察到数据变更, 新数据为:${it}, 当前状态为${lifecycle.currentState}") })
        // myLiveData.stringData.observeForever( Observer { println("观察到数据变更, 新数据为:${it}") })

        myLiveData.stringData.value = "在onCreate 中先变化一下"

        findViewById<Button>(R.id.modify_string_data).setOnClickListener {
            myLiveData.stringData.value = "点击修改了 stringData "
        }


        // 注册 userData 观察
        myLiveData.userData.observe(this, Observer {
            nameView.text = it.name
        })


        // 演示 Transformations.map() 和 switchMap()方法


        // map方法 接收两个参数，第一个参数是用于转换的LiveData原始对象，第二个参数是转换函数。
        // Map()函数可以用于对数据的封装

        // 每次我们给 stringData  设置值的时候  (LiveData.setValue 或者 LiveData.postValue)都会触发Transformations.map(),
        // 先进入 map方法, lamada 的参数是 myLiveData.stringData.value
        // map方法返回值是 LiveData 对象, 其value 就是 lamada 方法的返回值
        // 然后会进入 observe 回调, 参数是 上面map方法返回的 LiveData的value
        Transformations.map(myLiveData.stringData) {
            it.length

            // 也可以不返回值
            // Unit
        }
            .observe(this, Observer {
                lengthView.text = "字符串长度：${it} "
            })


        // switchMap()函数同样接收两个参数，第一个参数是一个LiveData对象，当此LivaData对象变化时就调用转换函数生成一新的LiveData对象，第二个参数就是转化函数。
        // switchMap()函数可用于LiveData对象不是直接在ViewModel中创建，而是调用其他方法创建的。

        // 如果我们不在这里使用switchMap的话，由于我们的repository每次返回的都是一个新的LiveData instance，会导致view observe多个LiveData的情形出现(就是Activity真对一个数据需要观察多个LiveData的变化)，而switchMap会保证view observe的LiveData的是同一个
        //
        // Google官方也提醒我们在使用LiveData的时候要避免对外暴露一个var类型的LiveData或者MutableLiveData，以避免重复observe

        // 一般链式调用接口, 这里我为了验证 switchMap 每次返回的都是同一个LiveData对象, 分开写了
        val switchMapLiveData = Transformations.switchMap(myLiveData.userData) {
            println("------${myLiveData.userData.value?.name}")
            it.age = 33

            // 模拟, 每次都返回一个新的 LiveData对象, 看看 switchMap 到底是不是只返回同一个LiveData对象
            val a = MutableLiveData(it)
            println("------在switchMap 中创建的新的LiveData: $a")
            a
        }

        switchMapLiveData.observe(
            this@LiveDataActivity,
            Observer { user: UserInfo -> println(user) })


        // 多次点击, 每次都 修改 userData 的数据, 就会触发switchMap中的方法执行, 然后延时看看这个 switchMapLiveData是否是同一个对象
        // 验证结果: 是同一个
        findViewById<Button>(R.id.modify_name_view).setOnClickListener {
            myLiveData.getUserInfo()
            Handler().postDelayed(
                { println("------switchMap 方法返回值: LiveData: ${switchMapLiveData} ") },
                1000
            )
        }


        // map 方法和 switchMap 方法区别:

        // map 方法的 第二个参数 lambda 返回值 是作为observe lambda 的参数

        // switchMap 方法 的第二个参数 lambda返回值必须是 LiveData, 它的 value 是作为observe lambda 的参数


        // 验证
        // myLiveData.placesLiveData.observe(this, Observer { })

        findViewById<Button>(R.id.modify_name_view2).setOnClickListener {
            myLiveData.setQueryName("1")
            Handler().postDelayed(
                { println("------switchMap 方法返回值: LiveData: ${myLiveData.placesLiveData} ") },
                1000
            )
        }


//演示:MediatorLiveData  通过改变数据源1或者2的数据，中间商接收到更新了
        myLiveData.liveDataMerger.observe(this,
            Observer {
                if (it is UserInfo) {

                    Toast.makeText(this@LiveDataActivity, "哈哈哈, userInfo来了", Toast.LENGTH_SHORT)
                        .show()

                } else if (it is String) {
                    Toast.makeText(this@LiveDataActivity, "哈哈哈, string来了", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        )
    }

}
