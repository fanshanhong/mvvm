package com.fan.databinding.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

/**
 * @Description:
 * @Author: fan
 * @Date: 2021-02-07 14:51
 * @Modify:
 */

open class Goods : BaseObservable() {

    @Bindable
    var name: String? = null
        set(value) {

            field = value

            // 通知UI, 如果使用了 BR.name , 就更新UI
            notifyPropertyChanged(BR.name)
        }

    var price: Float = 0.0f


    @Bindable
    var detail: String? = ""
        set(value) {

            field = value

            // 通知UI, 只要使用了该对象的字段的UI, 都更新
            // Notifies listeners that all properties of this instance have changed.
            notifyChange()
        }
}


// 我的疑问:
// 这里price, 没有用@Bindable, notifyChange  为啥也可以更新它?

// Java 也是这样么?

