package com.rasyidin.rannote.ui.feature.note

import android.annotation.SuppressLint
import android.content.Intent
import android.content.Intent.*
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.rasyidin.rannote.R
import com.rasyidin.rannote.core.domain.model.note.ColorPicker
import com.rasyidin.rannote.core.domain.model.note.Note
import com.rasyidin.rannote.core.utils.Constants.colorTheme
import com.rasyidin.rannote.databinding.ActivityAddUpdateNoteBinding
import com.rasyidin.rannote.ui.adapter.note.ColorAdapter
import com.rasyidin.rannote.ui.base.BaseActivity
import com.rasyidin.rannote.ui.helper.getCurrentDate
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Singleton

@AndroidEntryPoint
class AddUpdateNoteActivity : BaseActivity<ActivityAddUpdateNoteBinding>() {

    override fun getViewBinding() = ActivityAddUpdateNoteBinding.inflate(layoutInflater)

    private lateinit var botSheetBehavior: BottomSheetBehavior<*>

    private var botSheetState = true

    private var isDeleted = false

    private val args: AddUpdateNoteActivityArgs by navArgs()

    @Singleton
    private var themeColor: ColorPicker = ColorPicker()

    @Singleton
    private var note: Note = Note()

    private val colorAdapter: ColorAdapter by lazy {
        ColorAdapter()
    }

    private val noteViewModel: NoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = null

        checkArgs()

        setupBottomSheet()

        setupAdapter()

        selectThemeColor()

        deleteNote()

        shareNote()

        binding.imgBack.setOnClickListener {
            finish()
        }
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

    private fun deleteNote() {
        binding.imgDelete.setOnClickListener {
            getNotesFromView()
            lifecycleScope.launchWhenCreated {
                noteViewModel.deleteNote(note)
                isDeleted = true
            }
            finish()
        }
    }

    private fun selectThemeColor() {
        binding.imgColor.setOnClickListener {
            if (botSheetState) {
                botSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                botSheetState = false
                colorAdapter.onItemClickListener = { colorPicker ->
                    setNoteColor(colorPicker)
                    themeColor.colorCard = colorPicker.colorCard
                    themeColor.colorTitle = colorPicker.colorTitle
                    themeColor.colorDesc = colorPicker.colorDesc
                }
            } else {
                botSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                botSheetState = true
            }

        }
    }

    private fun setupAdapter() = binding.rvColor.apply {
        colorAdapter.setData(colorTheme)
        adapter = colorAdapter
        layoutManager =
            LinearLayoutManager(this@AddUpdateNoteActivity, LinearLayoutManager.HORIZONTAL, false)
        setHasFixedSize(true)
    }

    private fun setupBottomSheet() {
        botSheetBehavior = BottomSheetBehavior.from(binding.botSheet)
        botSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
    }

    private fun shareNote() {
        binding.imgShare.setOnClickListener {
            getNotesFromView()
            if (note.title.isNullOrEmpty() || note.desc.isNullOrEmpty()) {
                Toast.makeText(this, getString(R.string.empty_message), Toast.LENGTH_SHORT).show()
            } else {
                val sendIntent = Intent().apply {
                    action = ACTION_SEND
                    type = "text/plain"
                    putExtra(EXTRA_TEXT, note.desc)
                    putExtra(EXTRA_TITLE, note.title)
                }

                val shareIntent = createChooser(sendIntent, null)
                startActivity(shareIntent)
            }

        }
    }

    override fun onStop() {
        super.onStop()
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
        const val NOTE = "note"
    }
}