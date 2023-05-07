package com.example.matrixapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.matrixapp.R
import com.example.matrixapp.databinding.FragmentOnboardingBinding
import com.example.matrixapp.databinding.FragmentSplashBinding

class OnBoardingFragment : Fragment(){

    private val binding: FragmentOnboardingBinding by lazy { FragmentOnboardingBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
}