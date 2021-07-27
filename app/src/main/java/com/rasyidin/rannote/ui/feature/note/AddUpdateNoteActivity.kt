package com.rasyidin.rannote.ui.feature.note

import android.graphics.Color
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.rasyidin.rannote.core.utils.Constants.colorTheme
import com.rasyidin.rannote.databinding.ActivityAddUpdateNoteBinding
import com.rasyidin.rannote.ui.adapter.note.ColorAdapter
import com.rasyidin.rannote.ui.base.BaseActivity

class AddUpdateNoteActivity : BaseActivity<ActivityAddUpdateNoteBinding>() {

    override fun getViewBinding() = ActivityAddUpdateNoteBinding.inflate(layoutInflater)

    private lateinit var botSheetBehavior: BottomSheetBehavior<*>

    private var botSheetState = true

    private val colorAdapter: ColorAdapter by lazy {
        ColorAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = null

        setupBottomSheet()

        setupAdapter()

        binding.imgColor.setOnClickListener {
            if (botSheetState) {
                botSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                botSheetState = false
                colorAdapter.onItemClickListener = { colorPicker ->
                    binding.root.setBackgroundColor(Color.parseColor(colorPicker.color))
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
        const val COLOR_THEME = "color_theme"
    }
}