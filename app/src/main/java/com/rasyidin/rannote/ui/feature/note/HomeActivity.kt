package com.rasyidin.rannote.ui.feature.note

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.rasyidin.rannote.R
import com.rasyidin.rannote.databinding.ActivityHomeBinding
import com.rasyidin.rannote.ui.base.BaseActivity
import com.rasyidin.rannote.ui.feature.finance.FinanceActivity
import com.rasyidin.rannote.ui.feature.intro.OnBoardingPreference
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>(),
    NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var onBoardingPref: OnBoardingPreference

    override fun getViewBinding() = ActivityHomeBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*binding.tvHallo.text = "Hi, ${onBoardingPref.getUserOnBoardingPref().name}"*/
        setSupportActionBar(binding.appBarMain.toolbar)
        supportActionBar?.title = null
        binding.appBarMain.toolbar.elevation = 0F

        setupNavDrawable()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, NoteFragment())
                .commit()
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        when (item.itemId) {
            R.id.nav_finance -> {
                val intent = Intent(this, FinanceActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_note -> {
                fragment = NoteFragment()
            }
        }

        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .commit()
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setupNavDrawable() {
        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.appBarMain.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        val drawerLayout = binding.drawerLayout

        toggle.apply {
            isDrawerIndicatorEnabled = false
            setHomeAsUpIndicator(R.drawable.ic_menu)
            drawerLayout.addDrawerListener(this)
            toolbarNavigationClickListener = View.OnClickListener {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.close()
                } else {
                    drawerLayout.open()
                }
            }
            syncState()
        }

        binding.navView.setNavigationItemSelectedListener(this)

    }

}