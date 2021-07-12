package com.rasyidin.rannote.ui.feature.note

import android.os.Bundle
import com.rasyidin.rannote.databinding.ActivityAddUpdateNoteBinding
import com.rasyidin.rannote.ui.base.BaseActivity

class AddUpdateNoteActivity : BaseActivity<ActivityAddUpdateNoteBinding>() {

    override fun getViewBinding()= ActivityAddUpdateNoteBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}