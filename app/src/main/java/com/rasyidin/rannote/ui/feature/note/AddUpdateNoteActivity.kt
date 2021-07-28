package com.rasyidin.rannote.ui.feature.note

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
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

        setupBottomSheet()

        setupAdapter()

        selectThemeColor()

        binding.etDesc.addTextChangedListener(object : TextWatcher {
            var timer: CountDownTimer? = null
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                timer?.cancel()
                timer = object : CountDownTimer(1000, 1500) {
                    override fun onTick(millisUntilFinished: Long) {}

                    override fun onFinish() {
                        saveNote()
                        Toast.makeText(this@AddUpdateNoteActivity, "Saved", Toast.LENGTH_SHORT).show()
                    }
                }.start()
            }
        })

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

    companion object {
        const val NOTE = "note"
    }
}