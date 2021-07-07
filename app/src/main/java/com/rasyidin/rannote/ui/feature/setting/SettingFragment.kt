package com.rasyidin.rannote.ui.feature.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rasyidin.rannote.R

class SettingFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.setting_preference)

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