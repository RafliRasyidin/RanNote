package com.rasyidin.rannote.ui.feature.note

import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.rasyidin.rannote.R
import com.rasyidin.rannote.databinding.ActivityHomeBinding
import com.rasyidin.rannote.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    private lateinit var navNoteController: NavController

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun getViewBinding() = ActivityHomeBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.appBarMain.toolbar)
        supportActionBar?.title = null
        binding.appBarMain.toolbar.elevation = 0F

        setupBotNavView()

        setupNavDrawable()

        setupActionBarWithNavController(navNoteController, appBarConfiguration)

        binding.appBarMain.contentMain.fabAdd.setOnClickListener {
            showAddDialog()
        }

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

    private fun setupBotNavView() {
        binding.appBarMain.contentMain.botNavView.background = null
        binding.appBarMain.contentMain.botNavView.menu.getItem(1).isEnabled = false

        navNoteController = Navigation.findNavController(this, R.id.nav_host_fragment)
        binding.appBarMain.contentMain.botNavView.setupWithNavController(navNoteController)
    }

    private fun showAddDialog() {
        val action = arrayOf("Add Note", "Add To-do")
        MaterialAlertDialogBuilder(this)
            .setTitle(getString(R.string.title_add_dialog))
            .setItems(action) { dialog, which ->
                when (which) {
                    0 -> {
                        dialog.dismiss()
                    }
                    1 -> {
                        dialog.dismiss()
                    }
                }
            }
            .show()
    }

}