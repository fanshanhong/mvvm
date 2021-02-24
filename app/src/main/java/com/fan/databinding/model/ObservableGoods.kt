package com.fan.databinding.model

import androidx.databinding.ObservableField
import androidx.databinding.ObservableFloat

/**
 * 使用 ObservableField 的实体类
 */
class ObservableGoods(name: String, details: String, price: Float) {

    // 这里用val 就好了, 不需要变更name. 只需要调用它的set方法就好了
    val name: ObservableField<String> = ObservableField(name)
    val details: ObservableField<String> = ObservableField(details)
    val price: ObservableFloat = ObservableFloat(price)
}