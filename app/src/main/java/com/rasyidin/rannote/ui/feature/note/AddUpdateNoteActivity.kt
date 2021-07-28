package com.rasyidin.rannote.ui.feature.note

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.rasyidin.rannote.core.domain.model.note.ColorPicker
import com.rasyidin.rannote.core.domain.model.note.Note
import com.rasyidin.rannote.core.utils.Constants.colorTheme
import com.rasyidin.rannote.databinding.ActivityAddUpdateNoteBinding
import com.rasyidin.rannote.ui.adapter.note.ColorAdapter
import com.rasyidin.rannote.ui.base.BaseActivity
import com.rasyidin.rannote.ui.helper.getCurrentDate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddUpdateNoteActivity : BaseActivity<ActivityAddUpdateNoteBinding>() {

    override fun getViewBinding() = ActivityAddUpdateNoteBinding.inflate(layoutInflater)

    private lateinit var botSheetBehavior: BottomSheetBehavior<*>

    private var botSheetState = true

    private var isDeleted = false

    private val args: AddUpdateNoteActivityArgs by navArgs()

    private var themeColor: ColorPicker = ColorPicker()

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

        binding.etDesc.addTextChangedListener(object : TextWatcher {
            var timer: CountDownTimer? = null
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                timer?.cancel()
                timer = object : CountDownTimer(5000, 10000) {
                    override fun onTick(millisUntilFinished: Long) {}

                    override fun onFinish() {
                        saveNote()
                    }
                }.start()
            }
        })

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
            binding.root.setBackgroundColor(Color.parseColor(note.color))
            note.id = note.id
            themeColor.color = note.color
        } else {
            binding.root.setBackgroundColor(Color.parseColor(colorTheme[0].color))
        }
    }

    private fun getNotesFromView() {
        note.apply {
            title = binding.etTitle.text.toString()
            desc = binding.etDesc.text.toString()
            color = themeColor.color
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
                    binding.root.setBackgroundColor(Color.parseColor(colorPicker.color))
                    themeColor.color = colorPicker.color
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

    override fun onStop() {
        super.onStop()
        getNotesFromView()
        val isTitleEmpty = note.title.isNullOrEmpty()
        val isDescEmpty = note.desc.isNullOrEmpty()
        if (isTitleEmpty && isDescEmpty) {
            return
        } else if (isDeleted){
            return
        } else {
            saveNote()
        }
    }

    companion object {
        const val NOTE = "note"
    }
}