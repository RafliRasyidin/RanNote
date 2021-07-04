package com.rasyidin.rannote.ui.feature.intro

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro2
import com.github.appintro.AppIntroFragment
import com.rasyidin.rannote.R
import com.rasyidin.rannote.core.domain.model.intro.IntroSlideData.DATA_SLIDE
import com.rasyidin.rannote.ui.feature.note.NoteActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingActivity : AppIntro2() {

    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setIndicatorColor(
            ContextCompat.getColor(this, R.color.colorAccent),
            ContextCompat.getColor(this, R.color.colorPrimary),
        )

        isWizardMode = true

        setImmersiveMode()

        addSlide(
            AppIntroFragment.newInstance(
                title = DATA_SLIDE[0].title,
                description = DATA_SLIDE[0].desc,
                imageDrawable = R.color.colorBlackVariant,
                backgroundColor = ContextCompat.getColor(this, R.color.colorBackground),
                titleColor = ContextCompat.getColor(this, R.color.colorBlackVariant),
                ContextCompat.getColor(this, R.color.colorBlackVariant),
                titleTypefaceFontRes = R.font.poppins_semibold,
                descriptionTypefaceFontRes = R.font.poppins
            )
        )
        addSlide(
            AppIntroFragment.newInstance(
                title = DATA_SLIDE[1].title,
                description = DATA_SLIDE[1].desc,
                imageDrawable = R.drawable.ic_note,
                backgroundColor = ContextCompat.getColor(this, R.color.colorBackground),
                titleColor = ContextCompat.getColor(this, R.color.colorBlackVariant),
                ContextCompat.getColor(this, R.color.colorBlackVariant),
                titleTypefaceFontRes = R.font.poppins_semibold,
                descriptionTypefaceFontRes = R.font.poppins
            )
        )
        addSlide(
            AppIntroFragment.newInstance(
                title = DATA_SLIDE[2].title,
                description = DATA_SLIDE[2].desc,
                imageDrawable = R.drawable.ic_todo_list,
                backgroundColor = ContextCompat.getColor(this, R.color.colorBackground),
                titleColor = ContextCompat.getColor(this, R.color.colorBlackVariant),
                ContextCompat.getColor(this, R.color.colorBlackVariant),
                titleTypefaceFontRes = R.font.poppins_semibold,
                descriptionTypefaceFontRes = R.font.poppins
            )
        )
        addSlide(
            AppIntroFragment.newInstance(
                title = DATA_SLIDE[3].title,
                description = DATA_SLIDE[3].desc,
                imageDrawable = R.drawable.ic_finance,
                backgroundColor = ContextCompat.getColor(this, R.color.colorBackground),
                titleColor = ContextCompat.getColor(this, R.color.colorBlackVariant),
                ContextCompat.getColor(this, R.color.colorBlackVariant),
                titleTypefaceFontRes = R.font.poppins_semibold,
                descriptionTypefaceFontRes = R.font.poppins
            )
        )

        addSlide(OnBoardingFragment.newInstance())

    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        return
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        val intent = Intent(this, NoteActivity::class.java)
        startActivity(intent)
    }
}