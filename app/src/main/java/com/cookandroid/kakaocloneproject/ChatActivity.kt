package com.cookandroid.kakaocloneproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.MenuItem
import com.cookandroid.kakaocloneproject.databinding.ActivityChatBinding

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("test", "onCreate")

        // StatusBar 투명도 설정
        makeStatusBarTransparent()

        // toolbar back btn
        binding.chatBackBtn.setOnClickListener {
            finish()
        }

        // 암시적 인텐트로 카메라 호출
        binding.chatBottomMenuBtn.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(intent)
        }

    }

    override fun onStart() {
        super.onStart()
        Log.d("test", "onstart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("test", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("test", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("test", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("test", "onDestroy")
    }
}