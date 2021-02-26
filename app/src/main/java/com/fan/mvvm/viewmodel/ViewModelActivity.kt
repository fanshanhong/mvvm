package com.fan.mvvm.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.fan.mvvm.R


/**
 * 演示 ViewModel 处理屏幕旋转问题
 *
 */
class ViewModelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)

        val scoreView = findViewById<TextView>(R.id.score_view)

        val myViewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)


        findViewById<Button>(R.id.add_one_point).setOnClickListener {
            myViewModel.scoreTeamA = myViewModel.scoreTeamA + 1
            scoreView.text = myViewModel.scoreTeamA.toString()
        }

        scoreView.text = myViewModel.scoreTeamA.toString()
    }
}
