package com.example.matrixapp.view.fragments

import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.matrixapp.R
import com.example.matrixapp.app.App
import com.example.matrixapp.databinding.FragmentLoginBinding
import com.example.matrixapp.model.server.LoginPost
import com.example.matrixapp.view.activity.DrawerActivity
import com.example.matrixapp.viewmodel.LoginViewModel

class LoginFragment : Fragment() {

    private val binding: FragmentLoginBinding by lazy { FragmentLoginBinding.inflate(layoutInflater) }
    private var checkerPass = true
    private val viewModel : LoginViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        applyClick()
        setObservers()
    }

    private fun applyClick(){
        binding.forgotPasswordTxt.setOnClickListener { findNavController().navigate(R.id.action_loginFragment_to_resetPasswordFragment) }

        binding.logButton.setOnClickListener {
            viewModel.login(
                LoginPost(
                    binding.emailEditText.text.toString(),
                    binding.passwordEditText.text.toString(),
                ),
                requireContext()
            )
        }

        binding.registerTxt.setOnClickListener { findNavController().navigate(R.id.action_loginFragment_to_registrationFragment) }

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
    }
    private fun setObservers(){
        viewModel.successResponse.observe(viewLifecycleOwner){
            if (it) {
                requireContext().startActivity(Intent(activity, DrawerActivity::class.java))
                requireActivity().finish()
                App.dm.passLogin()
                viewModel.successResponse.value = false
            }
        }
    }
}