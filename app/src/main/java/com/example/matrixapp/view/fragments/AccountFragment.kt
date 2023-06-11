package com.example.matrixapp.view.fragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.matrixapp.R
import com.example.matrixapp.databinding.FragmentAccountBinding
import com.example.matrixapp.databinding.PremiumActivationDialogBinding
import com.example.matrixapp.databinding.ResetPasswordDialogLayoutBinding
import com.example.matrixapp.utils.Case
import com.example.matrixapp.view.activity.DrawerActivity
import com.example.matrixapp.viewmodel.AccountViewModel

class AccountFragment : Fragment() {

    private val binding: FragmentAccountBinding by lazy {
        FragmentAccountBinding.inflate(
            layoutInflater
        )
    }
    private val accountViewModel: AccountViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Case.actionId = R.id.action_accountFragment_to_pricingFragment
        applyClick()
        setObservers()
        getDeviceName()
    }

    private fun applyClick() {
        with(binding) {
            backLayout.setOnClickListener {
                findNavController().popBackStack()
            }
            pricingLayout.setOnClickListener {
                findNavController().navigate(R.id.action_accountFragment_to_pricingFragment)
            }
            restorePassLayout.setOnClickListener {
                findNavController().navigate(R.id.action_accountFragment_to_resetPasswordFragment2)
            }
            paymentsLayout.setOnClickListener {
                findNavController().navigate(R.id.action_accountFragment_to_paymentFragment)
            }
            statusLayout.setOnClickListener {
                val dialogBinding: PremiumActivationDialogBinding by lazy {
                    PremiumActivationDialogBinding.inflate(
                        layoutInflater
                    )
                }
                val dialog = Dialog(requireContext()).apply {
                    setCancelable(true)
                    setContentView(dialogBinding.root)
                    window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                }

                dialog.show()
                dialogBinding.positiveButton.setOnClickListener {
                    accountViewModel.premiumActivation(
                        requireActivity(),
                        dialogBinding.activationEditText.text.toString()
                    )
                    dialog.cancel()
                }
            }
        }
    }

    private fun getDeviceName() {
        binding.deviceNameTxt.text = Build.MODEL
    }

    private fun setObservers() {
        accountViewModel.statusOfAccount.observe(viewLifecycleOwner) {
            binding.statusLayout.isEnabled = !it
            if (it) {
                binding.statusText.text = resources.getString(R.string.activated)
            } else {
                binding.statusText.text = resources.getString(R.string.no_active)
            }
        }
    }
}