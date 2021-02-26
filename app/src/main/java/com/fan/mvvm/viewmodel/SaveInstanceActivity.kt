package com.fan.mvvm.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.fan.mvvm.R

/**
 * 演示原始方法,来处理屏幕旋转
 *
 * 使用 onSaveInstanceState  和  onRestoreInstanceState
 */
class SaveInstanceActivity : AppCompatActivity() {

    var score: Int = 0

    val scoreView : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_instance)

        Toast.makeText(this, "onCreate中, 最开始 score is ${score}", Toast.LENGTH_SHORT).show()

        val scoreView = findViewById<TextView>(R.id.score_view)

        findViewById<Button>(R.id.three_point_button).setOnClickListener {
            score += 3
            scoreView.setText("${score}")
        }

        println("------enter oncreate: ${savedInstanceState == null}")

        // 检查是否正在重新创建一个以前销毁的实例
        if (savedInstanceState != null) {
            // 从已保存状态恢复成员的值
            score = savedInstanceState.getInt("lastScore")
        } else {
            // 可能初始化一个新实例的默认值的成员
        }
        Toast.makeText(this, "从savedInstanceState中恢复: score is ${score}", Toast.LENGTH_SHORT).show()
        scoreView.text = "${score}"

    }

    override fun onSaveInstanceState(outState: Bundle) {
        println("------enter onSaveInstanceState")

        outState.putInt("lastScore", score)

        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        println("------enter onRestoreInstanceState: ${savedInstanceState == null}")

        // 从已保存状态恢复成员的值
        score = savedInstanceState.getInt("lastScore")

        scoreView?.text = "${score}"
    }

}
