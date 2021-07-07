package com.rasyidin.rannote.ui.feature.setting

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rasyidin.rannote.R
import com.rasyidin.rannote.databinding.FragmentAboutBinding
import com.rasyidin.rannote.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AboutFragment : BaseFragment<FragmentAboutBinding>(FragmentAboutBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = null
        hideBottomNavView()
    }

    private fun hideBottomNavView() {
        val navBar = (activity as AppCompatActivity).findViewById<BottomNavigationView>(R.id.bot_nav_view)
        navBar.visibility = View.GONE

        val appBar = (activity as AppCompatActivity).findViewById<BottomAppBar>(R.id.bot_app_bar)
        appBar.visibility = View.GONE

        val fabAdd = (activity as AppCompatActivity).findViewById<FloatingActionButton>(R.id.fab_add)
        fabAdd.visibility = View.GONE
    }
}