package com.cookandroid.kakaocloneproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class LoadingActivity : AppCompatActivity() {
    private val LOADING_TIME:Long = 800
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        makeStatusBarTransparent()

        // Handler를 이용해 액티비티 전환
        Handler().postDelayed({
            startActivity(Intent(this@LoadingActivity, MainActivity::class.java))
            finish()
        }, LOADING_TIME)
    }
}