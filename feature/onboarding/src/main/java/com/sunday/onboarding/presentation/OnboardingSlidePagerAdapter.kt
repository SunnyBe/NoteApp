package com.sunday.onboarding.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

private const val NUM_PAGES = 3
class OnboardingSlidePagerAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment = OnboardingFragment.getInstance(position)

}