package com.rasyidin.rannote.ui.feature.todo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rasyidin.rannote.R
import com.rasyidin.rannote.databinding.FragmentTodoBinding
import com.rasyidin.rannote.ui.base.BaseFragment

class TodoFragment : BaseFragment<FragmentTodoBinding>(FragmentTodoBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = null
    }

    override fun onResume() {
        super.onResume()
        val navBar = (activity as AppCompatActivity).findViewById<BottomNavigationView>(R.id.bot_nav_view)
        navBar.visibility = View.VISIBLE
    }
}