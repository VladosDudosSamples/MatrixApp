package com.example.matrixapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.matrixapp.R
import com.example.matrixapp.databinding.FragmentOnboardingBinding
import com.example.matrixapp.databinding.FragmentSplashBinding
import com.example.matrixapp.view.adapter.OnboardingAdapter
import com.example.matrixapp.viewmodel.OnboardingViewModel
import com.google.android.material.tabs.TabLayoutMediator

class OnBoardingFragment : Fragment() {

    private val binding: FragmentOnboardingBinding by lazy {
        FragmentOnboardingBinding.inflate(
            layoutInflater
        )
    }
    private val viewModel: OnboardingViewModel by viewModels()
    private lateinit var onboardingAdapter: OnboardingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
        setObservers()
    }

    private fun setObservers() {
        viewModel.isLastPage.observe(viewLifecycleOwner) {
            if (it) {
                binding.tvSkip.text = resources.getString(R.string.go_start)
            }
        }
    }

    private fun initViewPager() {
        binding.onboardingViewPager.apply {
            onboardingAdapter = OnboardingAdapter(requireActivity(), viewModel.items)
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            adapter = onboardingAdapter
        }
        (binding.onboardingViewPager[0] as RecyclerView).apply {
            overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }


        TabLayoutMediator(binding.tabDots, binding.onboardingViewPager) { tab, position ->

        }.attach()

    }
}

