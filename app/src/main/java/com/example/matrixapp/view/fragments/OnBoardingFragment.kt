package com.example.matrixapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.matrixapp.R
import com.example.matrixapp.databinding.FragmentOnboardingBinding
import com.example.matrixapp.utils.Utils.dpToPx
import com.example.matrixapp.view.adapter.OnboardingAdapter
import com.example.matrixapp.viewmodel.OnboardingViewModel

class OnBoardingFragment : Fragment() {

    private val binding: FragmentOnboardingBinding by lazy {
        FragmentOnboardingBinding.inflate(
            layoutInflater
        )
    }
    private val viewModel: OnboardingViewModel by viewModels()
    private lateinit var onBoardingAdapter: OnboardingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animateOpen()
        initViewPager()
        setObservers()
        setListeners()
        setAnimation()
    }

    private fun setObservers() {
        viewModel.isLastPage.observe(viewLifecycleOwner) {
            if (it) {
                binding.btnNext.text = resources.getString(R.string.go_start)
            } else {
                binding.btnNext.text = resources.getString(R.string.next)
            }
        }
        viewModel.isOnBoardingPassed.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(R.id.action_onBoardingFragment3_to_registrationFragment)
            }
        }
    }

    private fun setListeners() {
        with(binding) {
            btnNext.setOnClickListener {
                if (onboardingViewPager.currentItem == 2) {
                    viewModel.setOnBoardingPassed()
                } else {
                    onboardingViewPager.currentItem = onboardingViewPager.currentItem + 1
                }
            }
            tvSkip.setOnClickListener {
                viewModel.setOnBoardingPassed()
            }
        }
    }

    private fun initViewPager() {
        binding.onboardingViewPager.apply {
            onBoardingAdapter = OnboardingAdapter(requireActivity())
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            adapter = onBoardingAdapter

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    val padding = requireContext().dpToPx(8)
                    when (position) {
                        0 -> {
                            binding.indicator1.setImageResource(R.drawable.tab_indicator_selected)
                            binding.indicator2.setImageResource(R.drawable.tab_indicator_default)
                            binding.indicator3.setImageResource(R.drawable.tab_indicator_default)
                            binding.indicator1.setPadding(0, 0, 0, 0)
                            binding.indicator2.setPadding(padding, padding, padding, padding)
                            binding.indicator3.setPadding(padding, padding, padding, padding)
                            viewModel.setIsLastPage(false)
                        }
                        1 -> {
                            binding.indicator1.setImageResource(R.drawable.tab_indicator_default)
                            binding.indicator2.setImageResource(R.drawable.tab_indicator_selected)
                            binding.indicator3.setImageResource(R.drawable.tab_indicator_default)
                            binding.indicator1.setPadding(padding, padding, padding, padding)
                            binding.indicator2.setPadding(0, 0, 0, 0)
                            binding.indicator3.setPadding(padding, padding, padding, padding)
                            viewModel.setIsLastPage(false)
                        }
                        2 -> {
                            binding.indicator1.setImageResource(R.drawable.tab_indicator_default)
                            binding.indicator2.setImageResource(R.drawable.tab_indicator_default)
                            binding.indicator3.setImageResource(R.drawable.tab_indicator_selected)
                            binding.indicator1.setPadding(padding, padding, padding, padding)
                            binding.indicator2.setPadding(padding, padding, padding, padding)
                            binding.indicator3.setPadding(0, 0, 0, 0)
                            viewModel.setIsLastPage(true)
                        }
                    }
                }
            })
        }
        (binding.onboardingViewPager[0] as RecyclerView).apply {
            overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }
    }

    private fun setAnimation() {
        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.logo_transition_set)
        binding.animationLogo.startAnimation(animation)
        animation.setAnimationListener(object: AnimationListener{
            override fun onAnimationStart(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                binding.animationLogo.visibility = View.GONE
                binding.imageView.visibility = View.VISIBLE
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }

        })
    }

    private fun animateOpen(){
        binding.fakeAnimationImg.animate().apply {
            duration = 1200
            alpha(0f)
        }
    }
}