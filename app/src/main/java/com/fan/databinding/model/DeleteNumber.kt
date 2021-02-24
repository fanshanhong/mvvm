package com.fan.databinding.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

/**
 * @Description:
 * @Author: fan
 * @Date: 2021-02-24 14:44
 * @Modify:
 */
class DeleteNumber: BaseObservable() {

    @Bindable
    var number: Int = 0
    set(value) {
        field = value
        notifyPropertyChanged(BR.number)
    }


}

