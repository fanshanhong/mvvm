package com.fan.mvvm.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableArrayMap
import com.fan.mvvm.R
import kotlin.random.Random

/**
 * 演示 ObservableCollection 使用
 */
class Activity4 : AppCompatActivity() {

    var map: ObservableArrayMap<String, String> = ObservableArrayMap()

    var list: ObservableArrayList<String> = ObservableArrayList()

    var contentView: Activity4LayoutBinding? = null

    var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contentView =
            DataBindingUtil.setContentView<Activity4LayoutBinding>(this,
                R.layout.activity4_layout
            )

        contentView?.map = map
        contentView?.list = list
        contentView?.index = 0

        list.add("00")

    }

    /**
     * 向  List 中添加数据.
     * 证明: 只要 List 容器中的数据变化, UI会自动更新的. 可以看到UI上展示的list[0]不断变化
     */
    fun addDataToList(view: View) {
        list?.add(0, "   " + Random.nextInt(100))
    }

    /**
     * 修改 List 的 index 值.
     * 证明: index 变化, UI也会自动更新.
     */
    fun modifyIndex(view: View) {
        i++
        contentView?.index = i
    }

    /**
     * 修改map 容器中的值, 只要map 容器中的数据变化, UI会自动更新
     */
    fun modifyMapData(view: View) {
        map["name"] = "leavesC," + Random.nextInt(100)
    }
}
