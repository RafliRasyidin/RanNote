package com.rasyidin.rannote.di

import android.content.Context
import com.rasyidin.rannote.core.domain.model.intro.UserPreference
import com.rasyidin.rannote.ui.helper.Constants.IS_ALREADY_ON_BOARDING
import com.rasyidin.rannote.ui.helper.Constants.NAME_PREF
import com.rasyidin.rannote.ui.helper.Constants.ON_BOARDING_PREF_KEY
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OnBoardingPreference @Inject constructor(@ApplicationContext context: Context) {
    private val pref = context.getSharedPreferences(ON_BOARDING_PREF_KEY, Context.MODE_PRIVATE)

    fun saveOnBoarding(value: UserPreference) {
        val editor = pref.edit()
        editor.apply {
            putBoolean(IS_ALREADY_ON_BOARDING, value.isAlreadyOnBoarding)
            putString(NAME_PREF, value.name)
        }
        editor.apply()
    }

    fun getUserOnBoardingPref(): UserPreference {
        val onBoarding = UserPreference()
        onBoarding.apply {
            isAlreadyOnBoarding = pref.getBoolean(IS_ALREADY_ON_BOARDING, false)
            name = pref.getString(NAME_PREF, "Folks!")
        }
        return onBoarding
    }
}