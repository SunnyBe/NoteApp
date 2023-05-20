package com.sunday.onboarding.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.sunday.onboarding.R
import com.sunday.onboarding.databinding.FragmentOnboardingBinding

class OnboardingFragment : Fragment() {

    private var binding: FragmentOnboardingBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val position = requireArguments().getInt(ARG_POSITION)
        val onBoardingTitles = requireContext().resources.getStringArray(R.array.onboarding_titles)
        val onBoardingContents =
            requireContext().resources.getStringArray(R.array.onboarding_contents)

        with(binding!!) {
            onboardingTitle.text = onBoardingTitles[position]
            onboardingContent.text = onBoardingContents[position]
            Glide.with(requireContext()).load(R.drawable.business_person_male_taking_notes_svg).into(binding!!.onboardingImage)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        private const val ARG_POSITION = "ARG_POSITION"

        fun getInstance(position: Int) = OnboardingFragment().apply {
            arguments = bundleOf(ARG_POSITION to position)
        }
    }
}