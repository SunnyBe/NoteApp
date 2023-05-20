package com.sunday.onboarding.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.window.OnBackInvokedDispatcher
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.sunday.onboarding.databinding.ActivityOnboardingBinding

class OnboardingActivity : FragmentActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    private val onboardingPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            updateCircleMarker(binding, position)
        }
    }

    private val pagerAdapter = OnboardingSlidePagerAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.onboardingViewPager.adapter = pagerAdapter
        binding.onboardingViewPager.registerOnPageChangeCallback(onboardingPageChangeCallback)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (binding.onboardingViewPager.currentItem == 0) {
            super.onBackPressed()
        } else {
            binding.onboardingViewPager.currentItem = binding.onboardingViewPager.currentItem - 1
        }
    }

    override fun getOnBackInvokedDispatcher(): OnBackInvokedDispatcher {
        return super.getOnBackInvokedDispatcher()
    }

    private fun updateCircleMarker(binding: ActivityOnboardingBinding, position: Int) {
        // TODO, update the selected circle for position
    }

    companion object {
        fun startActivityIntent(
            context: Context
        ) {
            val intent = Intent(context, OnboardingActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

}