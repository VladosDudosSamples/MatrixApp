package com.example.matrixapp.view.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.matrixapp.R
import com.example.matrixapp.databinding.FragmentRegistrationBinding
import com.example.matrixapp.databinding.FragmentSplashBinding
import com.example.matrixapp.view.activity.DrawerActivity

class RegistrationFragment : Fragment() {

    private var checkerPass = true
    private var checkerRepeat = true
    private val binding: FragmentRegistrationBinding by lazy {
        FragmentRegistrationBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        applyClick()
    }

    private fun applyClick() {
        binding.logInTxt.setOnClickListener { findNavController().navigate(R.id.action_registrationFragment_to_loginFragment) }

        binding.termsTxt.setOnClickListener { }

        binding.regButton.setOnClickListener {
            requireContext().startActivity(Intent(activity, DrawerActivity::class.java))
            requireActivity().finish()
        }

        binding.privacyTxt.setOnClickListener { findNavController().navigate(R.id.action_registrationFragment_to_privacyFragment) }

        binding.showHideBtnPassword.setOnClickListener {
            if (checkerPass) {
                Glide.with(it).load(R.drawable.password_close_eye).into(it as ImageView)
                binding.passwordEditText.transformationMethod = null
                checkerPass = false
            } else {
                Glide.with(it).load(R.drawable.password_eye).into(it as ImageView)
                binding.passwordEditText.transformationMethod = PasswordTransformationMethod()
                checkerPass = true
            }
        }
        binding.showHideBtnRepeat.setOnClickListener {
            if (checkerRepeat) {
                Glide.with(it).load(R.drawable.password_close_eye).into(it as ImageView)
                binding.repeatPasswordEditText.transformationMethod = null
                checkerRepeat = false
            } else {
                Glide.with(it).load(R.drawable.password_eye).into(it as ImageView)
                binding.repeatPasswordEditText.transformationMethod = PasswordTransformationMethod()
                checkerRepeat = true
            }
        }
    }
}