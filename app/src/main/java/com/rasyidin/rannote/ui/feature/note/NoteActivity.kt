package com.rasyidin.rannote.ui.feature.note

import android.os.Bundle
import com.rasyidin.rannote.databinding.ActivityNoteBinding
import com.rasyidin.rannote.ui.base.BaseActivity
import com.rasyidin.rannote.ui.feature.intro.OnBoardingPreference
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NoteActivity : BaseActivity<ActivityNoteBinding>() {

    @Inject
    lateinit var onBoardingPref: OnBoardingPreference

    override fun getViewBinding() = ActivityNoteBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.tvHallo.text = "Hi, ${onBoardingPref.getUserOnBoardingPref().name}"
    }
}