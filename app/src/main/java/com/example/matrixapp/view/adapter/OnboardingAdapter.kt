package com.example.matrixapp.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.matrixapp.model.OnboardingItem
import com.example.matrixapp.view.fragments.OnboardingItemFragment

class OnboardingAdapter(
    private val fragmentActivity: FragmentActivity,
    private val items: List<OnboardingItem>
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return OnboardingItemFragment.newInstance(position)
    }

}