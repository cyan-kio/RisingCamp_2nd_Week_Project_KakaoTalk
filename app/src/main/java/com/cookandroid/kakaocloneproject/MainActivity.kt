package com.cookandroid.kakaocloneproject


import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewTreeObserver
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import com.cookandroid.kakaocloneproject.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var friendListFragment: FriendListFragment
    private lateinit var chatListFragment: ChatListFragment
    private lateinit var viewFragment: ViewFragment
    private lateinit var shoppingFragment: ShoppingFragment
    private lateinit var etcFragment: EtcFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("TEST", "onCreate()")
        // StatusBar 투명 설정 - ViewExtension.kt
        makeStatusBarTransparent()

        // BottomNavigationView 색 설정
        binding.mainBottomNavigationView.itemIconTintList = null

        // Fragment 선언
        friendListFragment = FriendListFragment()
        chatListFragment = ChatListFragment()
        viewFragment = ViewFragment()
        shoppingFragment = ShoppingFragment()
        etcFragment = EtcFragment()

        // Fragment 초기화
        changeFragment(friendListFragment)

        // SharedPreferences 초기화
        sharedPreferences = getSharedPreferences("data", MODE_PRIVATE)
    }

    override fun onStart() {
        super.onStart()
        // BottomNavigationView - 저장된 Item Id 값 호출
        var bottomViewId = sharedPreferences.getInt("getBottomViewId", -1)

        // 불러온 Item Id 값으로 Fragment Inflate
        if(bottomViewId == -1) {
            Log.d("TEST", "no Id")
            changeFragment(friendListFragment)
        } else {
            Log.d("TEST", "here is Id"+bottomViewId.toString())
            when (bottomViewId) {
                R.id.menu_friend_list -> {
                    changeFragment(friendListFragment)
                }
                R.id.menu_chat_list -> {
                    changeFragment(chatListFragment)
                }
                R.id.menu_view -> {
                    changeFragment(viewFragment)
                }
                R.id.menu_shopping -> {
                    changeFragment(shoppingFragment)
                }
                R.id.menu_etc -> {
                    changeFragment(etcFragment)
                }
            }
            // 선택된 Item 값 설정
            binding.mainBottomNavigationView.selectedItemId = bottomViewId
        }

        Log.d("TEST", "onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("TEST", "Resume()")

        // Fragment 화면 전환 로직
        binding.mainBottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_friend_list -> {
                    changeFragment(friendListFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.menu_chat_list -> {
                    changeFragment(chatListFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.menu_view -> {
                    changeFragment(viewFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.menu_shopping -> {
                    changeFragment(shoppingFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.menu_etc -> {
                    changeFragment(etcFragment)
                    return@setOnItemSelectedListener true
                }
            }
            true
        }
    }

    override fun onPause() {
        super.onPause()
        Log.d("TEST", "onPause()")
    }

    override fun onStop() {
        super.onStop()

        // BottomNavigationView - Item Id 값 저장
        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        editor.putInt("getBottomViewId", binding.mainBottomNavigationView.selectedItemId)
        editor.apply()
        Log.d("TEST", "onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TEST", "onDestroy()")
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
    }
}