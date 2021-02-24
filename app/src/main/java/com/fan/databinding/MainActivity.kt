package com.fan.databinding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

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
            else -> println("no activity")
        }
    }


}
