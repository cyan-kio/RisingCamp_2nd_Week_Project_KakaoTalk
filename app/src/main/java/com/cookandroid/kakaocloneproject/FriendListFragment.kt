package com.cookandroid.kakaocloneproject

import android.app.Activity
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.cookandroid.kakaocloneproject.databinding.FragmentFriendListBinding


class FriendListFragment : Fragment() {

    lateinit var sharedPreferences: SharedPreferences
    private var _binding : FragmentFriendListBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // sharedPreferences 선언
        sharedPreferences = requireActivity().getSharedPreferences("data", MODE_PRIVATE)

        Log.d("Test", "Fragment1: onCreate")
    }

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFriendListBinding.inflate(inflater, container, false)
        Log.d("Test", "Fragment1: onCreateView")
        Log.d("Test", "${sharedPreferences.getInt("getScrollValue", 0)}")

        // 로그인 상태값 삭제
        binding.friendListOptionBtn.setOnClickListener{
            val editor : SharedPreferences.Editor = sharedPreferences.edit()
            editor.remove("LOGIN_CHECK")
            editor.apply()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Test", "Fragment1: onViewCreated")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d("Test", "Fragment1: onViewStateRestored")
        // scrollView.scrollTo로 저장된 Y값 설정
        _binding!!.friendListSv.scrollTo(0, sharedPreferences.getInt("getScrollValue", 0))
        Log.d("Test", "${sharedPreferences.getInt("getScrollValue", 0)}")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Test", "Fragment1: onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Test", "Fragment1: onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Test", "Fragment1: onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Test", "Fragment1: onStop")
        // scrollView Y 값 저장
        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        editor.putInt("getScrollValue", binding.friendListSv.scrollY)
        editor.apply()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("Test", "Fragment1: onSaveInstanceStat")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("Test", "Fragment1: onDestroyView")
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Test", "Fragment1: onDestroy")
    }
}