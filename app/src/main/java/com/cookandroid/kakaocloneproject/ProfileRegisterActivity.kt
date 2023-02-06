package com.cookandroid.kakaocloneproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cookandroid.kakaocloneproject.databinding.ActivityProfileRegisterBinding

class ProfileRegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // StatusBar 투명도 설정
        makeStatusBarTransparent()

        binding.profileRegisterRequestBtn.setOnClickListener {
            startActivity(Intent(this@ProfileRegisterActivity, LoadingActivity::class.java))
            finish()
        }
    }
}