package com.rasyidin.rannote.ui.feature.intro

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.github.appintro.SlidePolicy
import com.rasyidin.rannote.R
import com.rasyidin.rannote.core.domain.model.intro.UserPreference
import com.rasyidin.rannote.databinding.FragmentOnboardingBinding
import com.rasyidin.rannote.di.OnBoardingPreference
import com.rasyidin.rannote.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OnBoardingFragment :
    BaseFragment<FragmentOnboardingBinding>(FragmentOnboardingBinding::inflate), SlidePolicy {

    @Inject
    lateinit var pref: OnBoardingPreference

    private var isBtnSaveClicked = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userPreference = UserPreference()

        binding.btnSave.setOnClickListener {
            val name = binding.etName.editText?.text.toString()
            if (name.isEmpty()) {
                binding.etName.error = resources.getString(R.string.et_name_empty)
            } else {
                binding.etName.error = null
                userPreference.name = name
                userPreference.isAlreadyOnBoarding = true
                pref.saveOnBoarding(userPreference)
                Toast.makeText(requireActivity(), "Hi, $name!", Toast.LENGTH_SHORT).show()
                isBtnSaveClicked = true
            }
        }
    }

    override val isPolicyRespected: Boolean
        get() = binding.etName.editText?.text!!.isNotEmpty() && isBtnSaveClicked

    override fun onUserIllegallyRequestedNextPage() {
        binding.etName.error = resources.getString(R.string.et_name_empty)
    }

    companion object {
        fun newInstance() = OnBoardingFragment()
    }
}