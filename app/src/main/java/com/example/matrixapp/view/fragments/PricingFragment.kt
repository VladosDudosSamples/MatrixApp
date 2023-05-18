package com.example.matrixapp.view.fragments

import android.app.Dialog
import android.app.MediaRouteButton
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
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.matrixapp.R
import com.example.matrixapp.databinding.FragmentPricingBinding
import com.example.matrixapp.databinding.PaymentDialogBinding
import com.example.matrixapp.view.adapter.LocationAdapter
import com.google.android.material.card.MaterialCardView
import com.google.android.material.radiobutton.MaterialRadioButton
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
        chooseCard(binding.freeCardView, binding.freeRb, binding.viewToHide)
    }

    private fun applyClick(){
        with(binding){
            closePricingBtn.setOnClickListener {
                findNavController().popBackStack()
            }
            buyBtn.setOnClickListener { showPricingDialog() }

            freeCardView.setOnClickListener {
                setDefault()
                chooseCard(freeCardView, freeRb, viewToHide)
            }
            baseCardView.setOnClickListener {
                setDefault()
                chooseCard(baseCardView, baseRb, viewToHideBase)
            }
            premiumCardView.setOnClickListener {
                setDefault()
                chooseCard(premiumCardView, premiumRb, viewToHidePremium)
            }
            individualCardView.setOnClickListener {
                setDefault()
                chooseCard(individualCardView, individualRb, viewToHideIndividual)
            }
        }
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

    private fun setDefault(){
        with(binding){
            freeCardView.strokeColor = getColor(requireContext(), R.color.pricing_card_stroke)
            baseCardView.strokeColor = getColor(requireContext(), R.color.pricing_card_stroke)
            premiumCardView.strokeColor = getColor(requireContext(), R.color.pricing_card_stroke)
            individualCardView.strokeColor = getColor(requireContext(), R.color.pricing_card_stroke)

            defaultVision()

            freeRb.isChecked = false
            baseRb.isChecked = false
            premiumRb.isChecked = false
            individualRb.isChecked = false
        }
    }

    private fun defaultVision(){
        with(binding){
            freeRb.visibility = View.INVISIBLE
            baseRb.visibility = View.INVISIBLE
            premiumRb.visibility = View.INVISIBLE
            individualRb.visibility = View.INVISIBLE

            viewToHide.visibility = View.VISIBLE
            viewToHideBase.visibility = View.VISIBLE
            viewToHidePremium.visibility = View.VISIBLE
            viewToHideIndividual.visibility = View.VISIBLE
        }
    }
    private fun chooseCard(card: MaterialCardView, rButton: MaterialRadioButton, view: View){
        rButton.visibility = View.VISIBLE
        view.visibility = View.INVISIBLE
        card.strokeColor = getColor(requireContext(), R.color.green)
        rButton.isChecked = true
    }
}