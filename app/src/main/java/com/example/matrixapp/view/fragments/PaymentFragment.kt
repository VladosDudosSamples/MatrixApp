package com.example.matrixapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.matrixapp.R
import com.example.matrixapp.databinding.FragmentPartnerBinding
import com.example.matrixapp.databinding.FragmentPaymentBinding
import com.example.matrixapp.model.PaymentItem
import com.example.matrixapp.model.PaymentListModel
import com.example.matrixapp.view.adapter.PaymentsItemsAdapter
import com.example.matrixapp.viewmodel.PaymentViewModel

class PaymentFragment : Fragment() {

    private val binding: FragmentPaymentBinding by lazy {
        FragmentPaymentBinding.inflate(
            layoutInflater
        )
    }
    private val paymentViewModel: PaymentViewModel by viewModels()
    private lateinit var adapter: PaymentsItemsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        paymentViewModel.getMonths()
        setObservers()
        applyClick()
        setAdapter()
        setSwitch()
    }

    private fun applyClick() {
        with(binding) {
            renewBtn.setOnClickListener {
                findNavController().navigate(R.id.action_paymentFragment_to_pricingFragment)
            }
            frameLayout.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun setAdapter() {
        with(binding) {
            adapter = PaymentsItemsAdapter(requireContext(), listOf())
            rvPaymentItems.adapter = adapter
        }
    }

    private fun setSwitch() {
        binding.switchTabs.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                val copyModel = mutableListOf<PaymentListModel>()
                paymentViewModel.monthsLifeData.value!!.forEach { item ->
                    if (item.payments.any { it.isActive }) {
                        copyModel.add(PaymentListModel(item.month, item.payments.filter { it.isActive }))
                    }
                }

                adapter.updateList(copyModel)
                paymentViewModel.currentListSize.value = copyModel.size
            } else {
                adapter.updateList(paymentViewModel.monthsLifeData.value ?: mutableListOf())
                paymentViewModel.currentListSize.value = paymentViewModel.monthsLifeData.value?.size
            }
        }
    }

    private fun setObservers() {
        paymentViewModel.monthsLifeData.observe(viewLifecycleOwner) {
            adapter.updateList(it)
        }
        paymentViewModel.currentListSize.observe(viewLifecycleOwner) {
            if (it == 0) binding.imgEmptyList.visibility = View.VISIBLE
            else binding.imgEmptyList.visibility = View.GONE
        }
    }
}