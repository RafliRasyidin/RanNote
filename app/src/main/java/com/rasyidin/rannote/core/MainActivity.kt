package com.rasyidin.rannote.core

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.rasyidin.rannote.databinding.ActivityMainBinding
import com.rasyidin.rannote.ui.base.BaseActivity
import com.rasyidin.rannote.ui.feature.intro.OnBoardingActivity
import com.rasyidin.rannote.core.di.OnBoardingPreference
import com.rasyidin.rannote.feature_note.presentation.note.HomeActivity
import com.rasyidin.rannote.ui.helper.Constants.SPLASH_DELAY
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    @Inject
    lateinit var onBoardingPreference: OnBoardingPreference

    private val handler = Handler(Looper.getMainLooper())
    private val runnable = Runnable {
        val isAlreadyOnBoarding = onBoardingPreference.getUserOnBoardingPref().isAlreadyOnBoarding
        if (isAlreadyOnBoarding) {
            Intent(this, HomeActivity::class.java).also {
                startActivity(it)
            }
        } else {
            Intent(this, OnBoardingActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handler.postDelayed(runnable, SPLASH_DELAY)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacks(runnable)
    }


}