package com.example.matrixapp.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.matrixapp.R
import com.example.matrixapp.app.App
import com.example.matrixapp.databinding.FragmentSplashBinding
import com.example.matrixapp.view.activity.DrawerActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {
    private val binding: FragmentSplashBinding by lazy { FragmentSplashBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            delay(2000)
            if(App.dm.isLoginPassed()){
                requireContext().startActivity(Intent(activity, DrawerActivity::class.java))
                requireActivity().finish()
            }
            else if (App.dm.isOnBoardingPassed()){
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            }
            else{
                findNavController().navigate(R.id.action_splashFragment_to_onBoardingFragment3)
            }
        }
    }
}