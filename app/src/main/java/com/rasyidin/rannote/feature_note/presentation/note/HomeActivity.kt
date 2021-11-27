package com.rasyidin.rannote.feature_note.presentation.note

import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.*
import com.rasyidin.rannote.R
import com.rasyidin.rannote.feature_note.domain.model.Note
import com.rasyidin.rannote.databinding.ActivityHomeBinding
import com.rasyidin.rannote.ui.base.BaseActivity
import com.rasyidin.rannote.feature_note.presentation.detail_note.DetailNoteFragment.Companion.ARG_NOTE
import com.rasyidin.rannote.feature_note.presentation.note.dialog.AddDialog
import com.rasyidin.rannote.feature_note.presentation.note.dialog.AddDialogListener
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
            AddDialog(this, object : AddDialogListener {
                override fun navigateToAddDetailNote(note: Note) {
                    val args = Bundle().apply {
                        putParcelable(ARG_NOTE, note)
                    }
                    navNoteController.navigate(R.id.detailNoteFragment, args)
                }

                override fun navigateToAddDetailTodo() {
                    navNoteController.navigate(R.id.detailAddTodoFragment)
                }
            }).show()
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

}