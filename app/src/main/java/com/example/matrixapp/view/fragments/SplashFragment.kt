package com.example.matrixapp.view.fragments

import android.animation.Animator
import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.view.Display
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
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
    private lateinit var display: Display

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        display = requireActivity().windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val height = size.y / 4
        animate(height)
    }
    private fun animate(height: Int){
        binding.textAnim.animate().setDuration(500).translationYBy(height.toFloat()).setListener(object : Animator.AnimatorListener{
            override fun onAnimationStart(p0: Animator) {
                chooseFragment()
            }

            override fun onAnimationEnd(p0: Animator) {
            }

            override fun onAnimationCancel(p0: Animator) {
            }

            override fun onAnimationRepeat(p0: Animator) {
            }
        })
    }
    private fun chooseFragment(){
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