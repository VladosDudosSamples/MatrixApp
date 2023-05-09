package com.example.matrixapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.matrixapp.databinding.FragmentOnboardingItemBinding
import com.example.matrixapp.viewmodel.OnBoardingViewModel


class OnboardingItemFragment : Fragment() {

    private val binding: FragmentOnboardingItemBinding by lazy{
        FragmentOnboardingItemBinding.inflate(layoutInflater)
    }

    private val viewModel: OnBoardingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            val position = arguments?.getInt("position") ?: 0
            val item = viewModel.items[position]
            tvDescription.text = item.description
            onboardingImage.setImageResource(item.image)
        }
    }

    companion object {
        fun newInstance(position: Int): OnboardingItemFragment {
            return OnboardingItemFragment().apply {
                arguments = Bundle().apply { putInt("position", position) }
            }
        }
    }


}