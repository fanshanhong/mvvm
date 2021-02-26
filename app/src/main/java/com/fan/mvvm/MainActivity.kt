package com.fan.mvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.fan.mvvm.databinding.*
import com.fan.mvvm.lifecycle.LifecycleActivity
import com.fan.mvvm.livedata.LiveDataActivity
import com.fan.mvvm.viewmodel.SaveInstanceActivity
import com.fan.mvvm.viewmodel.ViewModelActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<Button>(R.id.go_to_activity1).setOnClickListener(this)
        findViewById<Button>(R.id.go_to_activity2).setOnClickListener(this)
        findViewById<Button>(R.id.go_to_activity3).setOnClickListener(this)
        findViewById<Button>(R.id.go_to_activity4).setOnClickListener(this)
        findViewById<Button>(R.id.go_to_activity5).setOnClickListener(this)
        findViewById<Button>(R.id.go_to_activity6).setOnClickListener(this)
        findViewById<Button>(R.id.go_to_activity7).setOnClickListener(this)
        findViewById<Button>(R.id.go_to_activity8).setOnClickListener(this)
        findViewById<Button>(R.id.save_instance_activity).setOnClickListener(this)
        findViewById<Button>(R.id.view_model_activity).setOnClickListener(this)
        findViewById<Button>(R.id.live_data_activity).setOnClickListener(this)
        findViewById<Button>(R.id.lifecycle_activity).setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.go_to_activity1 -> startActivity(Intent(this, Activity1::class.java))
            R.id.go_to_activity2 -> startActivity(Intent(this, Activity2::class.java))
            R.id.go_to_activity3 -> startActivity(Intent(this, Activity3::class.java))
            R.id.go_to_activity4 -> startActivity(Intent(this, Activity4::class.java))
            R.id.go_to_activity5 -> startActivity(Intent(this, Activity5::class.java))
            R.id.go_to_activity6 -> startActivity(Intent(this, Activity6::class.java))
            R.id.go_to_activity7 -> startActivity(Intent(this, Activity7::class.java))
            R.id.go_to_activity8 -> startActivity(Intent(this, Activity8::class.java))
            R.id.save_instance_activity -> startActivity(Intent(this, SaveInstanceActivity::class.java))
            R.id.view_model_activity -> startActivity(Intent(this, ViewModelActivity::class.java))
            R.id.live_data_activity -> startActivity(Intent(this, LiveDataActivity::class.java))
            R.id.lifecycle_activity -> startActivity(Intent(this, LifecycleActivity::class.java))
            else -> println("no activity")
        }
    }


}
