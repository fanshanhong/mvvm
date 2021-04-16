package com.fan.mvvm.databinding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.fan.mvvm.R

/**
 * @Description: 展示DataBinding 在Fragment 中的使用
 * @Author: fan
 * @Date: 2021-02-07 14:34
 * @Modify:
 */
class MyFragment : Fragment() {

    lateinit var button:Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)


        // fragment_my_layout 中的 data 没有指定 class, inflate类型自动生成, 为 FragmentMyLayoutBinding
        // 如果在 fragment_my_layout 中的 data 有指定 class, 那inflate类型就是指定的类型

        val inflate =
            DataBindingUtil.inflate<FragmentMyLayoutBinding>(
                inflater,
                R.layout.fragment_my_layout,
                container,
                false
            )


        if (inflate is FragmentMyLayoutBinding) {
            println("inflate is FragmentMyLayoutBinding")
        } else {
            println("inflate is not FragmentMyLayoutBinding")
        }

        // 给属性赋值, 这里是调用了对应的set方法, 内容直接反映到view上了
        inflate.data1 = "xxx"


        // 这样也不生效
        // inflate.root.findViewById<TextView>(R.id.data2_view).setText("ahahahah")


        // 直接给view 设置text, 这样不生效
        // inflate.data2View.setText("哈哈哈")
        // inflate.data2View.text = "111111"

        inflate.data2 = "啊哈哈哈哈" // 等价于 inflate.setData2("11122")


        return inflate.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val button = view.findViewById<Button>(R.id.button)
        val data2View = view.findViewById<TextView>(R.id.data2_view)

        // 这样设置可以生效
        button.setOnClickListener {
            data2View.text="点击 button 修改了"
        }


    }

}