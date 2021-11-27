package com.rasyidin.rannote.feature_note.presentation.detail_note

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rasyidin.rannote.R
import com.rasyidin.rannote.core.utils.Constants.colorTheme
import com.rasyidin.rannote.databinding.FragmentDetailNoteBinding
import com.rasyidin.rannote.feature_note.domain.model.ColorPicker
import com.rasyidin.rannote.feature_note.domain.model.Note
import com.rasyidin.rannote.ui.base.BaseFragment
import com.rasyidin.rannote.ui.helper.getCurrentDate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Singleton

@AndroidEntryPoint
class DetailNoteFragment :
    BaseFragment<FragmentDetailNoteBinding>(FragmentDetailNoteBinding::inflate) {

    private var isDeleted = false

    private val args: DetailNoteFragmentArgs by navArgs()

    @Singleton
    private var themeColor: ColorPicker = ColorPicker()

    @Singleton
    private var note: Note = Note()

    private val noteViewModel: DetailNoteViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.hide()
        (activity as AppCompatActivity).supportActionBar?.title = null

        hideBotNavigation()

        checkArgs()

        selectThemeColor()

        shareNote()

        deleteNote()

        navigateToHome()

    }

    private fun hideBotNavigation() {
        val navBar =
            (activity as AppCompatActivity).findViewById<BottomNavigationView>(R.id.bot_nav_view)
        navBar.visibility = View.GONE

        val appBar = (activity as AppCompatActivity).findViewById<BottomAppBar>(R.id.bot_app_bar)
        appBar.visibility = View.GONE

        val fabAdd =
            (activity as AppCompatActivity).findViewById<FloatingActionButton>(R.id.fab_add)
        fabAdd.visibility = View.GONE
    }

    @SuppressLint("Range")
    private fun checkArgs() {
        note = args.note
        if (note.id != 0) {
            binding.etTitle.setText(note.title)
            binding.etDesc.setText(note.desc)
            setNoteColor(note)

            note.id = note.id
            themeColor.colorCard = note.colorCard
            themeColor.colorTitle = note.colorTitle
            themeColor.colorDesc = note.colorDesc
        } else {
            binding.apply {
                root.setBackgroundColor(Color.parseColor(colorTheme[0].colorCard))
                etTitle.setTextColor(Color.parseColor(colorTheme[0].colorTitle))
                etDesc.setTextColor(Color.parseColor(colorTheme[0].colorDesc))
            }

        }
    }

    private fun navigateToHome() {
        binding.imgBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    @SuppressLint("Range")
    private fun setNoteColor(note: Note) {
        binding.apply {
            etDesc.setTextColor(Color.parseColor(note.colorDesc))
            root.setBackgroundColor(Color.parseColor(note.colorCard))
            etTitle.setTextColor(Color.parseColor(note.colorTitle))
        }
    }

    @SuppressLint("Range")
    private fun setNoteColor(note: ColorPicker) {
        binding.apply {
            etDesc.setTextColor(Color.parseColor(note.colorDesc))
            root.setBackgroundColor(Color.parseColor(note.colorCard))
            etTitle.setTextColor(Color.parseColor(note.colorTitle))
        }
    }

    private fun getNotesFromView() {
        note.apply {
            title = binding.etTitle.text.toString()
            desc = binding.etDesc.text.toString()
            colorCard = themeColor.colorCard
            colorTitle = themeColor.colorTitle
            colorDesc = themeColor.colorDesc
            date = getCurrentDate()
        }
    }

    private fun saveNote() {
        getNotesFromView()
        lifecycleScope.launchWhenCreated {
            noteViewModel.saveNote(note)

        }
    }

    private fun observeEvent() {
        lifecycleScope.launchWhenCreated {
            noteViewModel.eventFlow.collectLatest { event ->
                when (event) {
                    is DetailNoteViewModel.UiEvent.GetException -> {
                        Toast.makeText(requireActivity(), event.messageError, Toast.LENGTH_SHORT).show()
                    }
                    is DetailNoteViewModel.UiEvent.SaveNote -> Unit
                    is DetailNoteViewModel.UiEvent.DeleteNote -> {
                        findNavController().popBackStack()
                    }
                }
            }
        }
    }

    private fun deleteNote() {
        binding.imgDelete.setOnClickListener {
            getNotesFromView()
            lifecycleScope.launchWhenCreated {
                noteViewModel.deleteNote(note)
                isDeleted = true
                observeEvent()
            }
        }
    }

    private fun selectThemeColor() {
        binding.imgColor.setOnClickListener {
            val botSheetPickColor = SheetPickColor.newInstance(colorTheme)
            botSheetPickColor.show(childFragmentManager, SheetPickColor.TAG)
            botSheetPickColor.onItemCLickCallback = { colorPicker ->
                setNoteColor(colorPicker)
                themeColor.apply {
                    colorCard = colorPicker.colorCard
                    colorTitle = colorPicker.colorTitle
                    themeColor.colorDesc = colorPicker.colorDesc

                }
            }
        }
    }

    private fun shareNote() {
        binding.imgShare.setOnClickListener {
            getNotesFromView()
            if (note.title.isNullOrEmpty() || note.desc.isNullOrEmpty()) {
                Toast.makeText(
                    requireActivity(),
                    getString(R.string.empty_message),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val sendIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, note.desc)
                    putExtra(Intent.EXTRA_TITLE, note.title)
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        getNotesFromView()
        val isTitleEmpty = note.title.isNullOrEmpty()
        val isDescEmpty = note.desc.isNullOrEmpty()
        if (isTitleEmpty && isDescEmpty) {
            return
        } else if (isDeleted) {
            return
        } else {
            saveNote()
        }
    }

    companion object {
        const val ARG_NOTE = "note"
    }
}