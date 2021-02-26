package com.fan.mvvm.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

/**
 * @Description:
 * @Author: fan
 * @Date: 2021-02-07 13:57
 * @Modify:
 */

class User : BaseObservable(){

    @Bindable
    var name: String? = null
    set(value) {
        field = value
        notifyPropertyChanged(BR.name)
    }

    var age: Int? = null
}