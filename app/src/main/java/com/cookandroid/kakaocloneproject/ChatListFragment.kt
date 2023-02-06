package com.cookandroid.kakaocloneproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cookandroid.kakaocloneproject.databinding.FragmentChatListBinding
import com.cookandroid.kakaocloneproject.databinding.FragmentFriendListBinding

class ChatListFragment : Fragment() {
    private lateinit var binding : FragmentChatListBinding
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Test", "Fragment2: onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d("Test", "Fragment2: onCreateView")
        binding = FragmentChatListBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Test", "Fragment2: onViewCreated")
        // 명시적 인텐트를 이용해 ChatActivity로 전환
        binding.chatListChat.setOnClickListener {
            startActivity(Intent(requireActivity(), ChatActivity::class.java))
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d("Test", "Fragment2: onViewStateRestored")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Test", "Fragment2: onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Test", "Fragment2: onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Test", "Fragment2: onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Test", "Fragment2: onStop")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("Test", "Fragment2: onSaveInstanceStat")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("Test", "Fragment2: onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Test", "Fragment2: onDestroy")
    }
}