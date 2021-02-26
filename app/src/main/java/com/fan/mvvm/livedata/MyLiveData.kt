package com.fan.mvvm.livedata

import androidx.lifecycle.*
import com.fan.mvvm.model.UserInfo

/**
 * @Description:
 * @Author: fan
 * @Date: 2021-02-25 09:28
 * @Modify:
 */
class MyLiveData : ViewModel() {

    val stringData: MutableLiveData<String> = MutableLiveData()

    var userData: MutableLiveData<UserInfo> = MutableLiveData()

    /**
     *  模拟获取数据
     */
    fun getUserInfo() {
        val user = UserInfo("李四", (10..50).random())
        userData.postValue(user)
    }


    private val queryLiveData = MutableLiveData<String>()

    fun setQueryName(name: String) {
        queryLiveData.value = name
    }

    val placesLiveData = Transformations.switchMap(queryLiveData) {
        //当queryLiveData的值变化时就会触发转化函数
            name ->
        MutableLiveData(name)
    }


    // 演示: MediatorLiveData
    val liveDataMerger: MediatorLiveData<Any> = MediatorLiveData()

    init {
        liveDataMerger.addSource(
            stringData,
            Observer { value -> liveDataMerger.value = value })//数据源1更新后通知中间商更新);
        liveDataMerger.addSource(
            userData,
            Observer { value -> liveDataMerger.value = value })//数据源2更新后通知中间商更新);
    }

}