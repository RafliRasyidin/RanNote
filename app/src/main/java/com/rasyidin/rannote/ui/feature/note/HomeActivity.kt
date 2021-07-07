package com.rasyidin.rannote.ui.feature.note

import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.*
import com.rasyidin.rannote.R
import com.rasyidin.rannote.databinding.ActivityHomeBinding
import com.rasyidin.rannote.ui.base.BaseActivity
import com.rasyidin.rannote.di.OnBoardingPreference
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    @Inject
    lateinit var onBoardingPref: OnBoardingPreference

    private lateinit var navNoteController: NavController

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun getViewBinding() = ActivityHomeBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*binding.tvHallo.text = "Hi, ${onBoardingPref.getUserOnBoardingPref().name}"*/
        setSupportActionBar(binding.appBarMain.toolbar)
        supportActionBar?.title = null
        binding.appBarMain.toolbar.elevation = 0F

        binding.appBarMain.contentMain.botNavView.background = null
        binding.appBarMain.contentMain.botNavView.menu.getItem(1).isEnabled = false

        navNoteController = Navigation.findNavController(this, R.id.nav_host_fragment)
        binding.appBarMain.contentMain.botNavView.setupWithNavController(navNoteController)

        setupNavDrawable()

        setupActionBarWithNavController(navNoteController, appBarConfiguration)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navNoteController) || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp() || navNoteController.navigateUp(appBarConfiguration)
    }

    private fun setupNavDrawable() {

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.noteFragment,
                R.id.financeActivity,
                R.id.aboutFragment,
                R.id.settingFragment
            ),
            binding.drawerLayout
        )

        binding.navView.setupWithNavController(navNoteController)

    }

}