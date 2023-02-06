package com.cookandroid.kakaocloneproject

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.cookandroid.kakaocloneproject.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // StatusBar 투명 설정
        makeStatusBarTransparent()

        // 로그인 상태 값 확인 후 액비비티 전환
        sharedPreferences = getSharedPreferences("data", MODE_PRIVATE)
        if(sharedPreferences.getString("LOGIN_CHECK", "UNCHECK") != "UNCHECK") {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onResume() {
        super.onResume()

        // 로그인 로직
        binding.loginLoginBtn.setOnClickListener {
            val loginId = binding.loginEmailEdt.text.toString()
            val loginPw = binding.loginPasswordEdt.text.toString()
            val checkId = sharedPreferences.getString("loginId", "test")
            val checkPw = sharedPreferences.getString("loginPw", "1234")

            if(checkId == loginId && checkPw == loginPw){
                val editor : SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString("LOGIN_CHECK", "CHECK")
                editor.apply()
                startActivity(Intent(this@LoginActivity, ProfileRegisterActivity::class.java))
                finish()
            }
        }

    }
}