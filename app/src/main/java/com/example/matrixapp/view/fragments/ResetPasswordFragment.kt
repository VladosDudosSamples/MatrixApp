package com.example.matrixapp.view.fragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.matrixapp.R
import com.example.matrixapp.databinding.FragmentResetPasswordBinding
import com.example.matrixapp.databinding.ResetPasswordDialogLayoutBinding
import com.example.matrixapp.viewmodel.ResetPasswordViewModel

class ResetPasswordFragment : Fragment() {

    private val binding: FragmentResetPasswordBinding by lazy {
        FragmentResetPasswordBinding.inflate(
            layoutInflater
        )
    }
    private val resetPasswordViewModel: ResetPasswordViewModel by viewModels()
    private var checkerPass = true
    private var checkerRepeat = true
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObservers()
        applyClick()
    }

    private fun setObservers() {
        resetPasswordViewModel.isEmailEntered.observe(viewLifecycleOwner){
            if (it) visionPassword()
            else visionEmail()
        }
    }
    private fun visionEmail(){
        binding.textView2.visibility = View.VISIBLE
        binding.emailEditText.visibility = View.VISIBLE
        binding.passwordLayout.visibility = View.GONE
        binding.repeatPasswordLayout.visibility = View.GONE
    }

    private fun visionPassword(){
        binding.textView2.visibility = View.GONE
        binding.emailEditText.visibility = View.GONE
        binding.passwordLayout.visibility = View.VISIBLE
        binding.repeatPasswordLayout.visibility = View.VISIBLE
    }
    private fun applyClick(){
        binding.resetBtn.setOnClickListener {
            if (resetPasswordViewModel.isEmailEntered.value == true){
                resetPassword()
            }
            else resetPasswordViewModel.isEmailEntered.value = true
        }
        binding.contactSupportTxt.setOnClickListener {
            findNavController().navigate(R.id.action_resetPasswordFragment_to_supportFragment2)
        }
        binding.backFrameBtn.setOnClickListener { findNavController().popBackStack() }
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
    private fun resetPassword(){
        val dialogBinding: ResetPasswordDialogLayoutBinding by lazy {ResetPasswordDialogLayoutBinding.inflate(layoutInflater)}
        val dialog = Dialog(requireContext()).apply {
            setCancelable(false)
            setContentView(dialogBinding.root)
            window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        dialog.show()
        dialogBinding.positiveButton.setOnClickListener {
            findNavController().popBackStack()
            dialog.cancel()
        }
    }
}