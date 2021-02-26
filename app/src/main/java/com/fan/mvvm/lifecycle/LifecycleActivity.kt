package com.fan.mvvm.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fan.mvvm.R

class LifecycleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)

        getLifecycle().addObserver(MyPresenter()) //添加LifecycleObserver
    }
}
