package com.rasyidin.rannote

import android.os.Bundle
import com.rasyidin.rannote.databinding.ActivityMainBinding
import com.rasyidin.rannote.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}