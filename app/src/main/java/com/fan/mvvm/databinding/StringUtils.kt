package com.fan.mvvm.databinding

import android.view.View


/**
 * 顶层函数
 * 演示在xml中使用顶层函数
 */
fun upperCase(str: String): String {
    return str
        .toUpperCase()
}

fun testOnClickTop(view:View) {
    println("-----testOnClickTop")
}

//kotlin顶层函数和属性
const val CONSTANT_STRING = "test string"
val VAL_STRING = "test string"
fun testFunction() {

}

object StringUtils {

    fun lowerCase(str: String): String {
        return str.toLowerCase()
    }

}