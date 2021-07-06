package com.rasyidin.rannote.ui.feature.finance

import android.os.Bundle
import com.rasyidin.rannote.databinding.ActivityFinanceBinding
import com.rasyidin.rannote.ui.base.BaseActivity

class FinanceActivity : BaseActivity<ActivityFinanceBinding>() {

    override fun getViewBinding() = ActivityFinanceBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}