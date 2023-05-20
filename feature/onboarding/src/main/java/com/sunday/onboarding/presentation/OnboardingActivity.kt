package com.sunday.onboarding.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.window.OnBackInvokedDispatcher
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.widget.ViewPager2
import com.sunday.cache.datastore.AggregateDataStore
import com.sunday.onboarding.databinding.ActivityOnboardingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class OnboardingActivity : FragmentActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    @Inject
    lateinit var aggregateDataStore: AggregateDataStore

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

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                Timber.e(javaClass.name + ":::: App Start count: IsIncrementing?")
                aggregateDataStore.incrementAppStartCount()
            }
        }
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