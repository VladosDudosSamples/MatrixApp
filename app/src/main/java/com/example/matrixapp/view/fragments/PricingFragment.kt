package com.example.matrixapp.view.fragments

import android.app.Dialog
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.matrixapp.R
import com.example.matrixapp.databinding.FragmentPricingBinding
import com.example.matrixapp.databinding.PaymentDialogBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class PricingFragment : Fragment() {

    private val binding: FragmentPricingBinding by lazy { FragmentPricingBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        applyClick()
    }

    private fun applyClick(){
        binding.closePricingBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.buyBtn.setOnClickListener { showPricingDialog() }
    }

    private fun showPricingDialog(){
        val dialogBinding: PaymentDialogBinding by lazy { PaymentDialogBinding.inflate(layoutInflater)}
        val dialog = Dialog(requireContext()).apply {
            setCancelable(false)
            setContentView(dialogBinding.root)
            window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        dialog.show()

        lifecycleScope.launch {
            delay(3000)
            dialog.cancel()
        }
    }
}