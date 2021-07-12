package com.rasyidin.rannote.ui.feature.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rasyidin.rannote.databinding.ActivityAddUpdateTodoBinding
import com.rasyidin.rannote.ui.base.BaseActivity

class AddUpdateTodoActivity : BaseActivity<ActivityAddUpdateTodoBinding>() {

    override fun getViewBinding() = ActivityAddUpdateTodoBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}