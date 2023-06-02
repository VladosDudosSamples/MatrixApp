package com.example.matrixapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.matrixapp.R
import com.example.matrixapp.databinding.FragmentPartnerBinding
import com.example.matrixapp.utils.Case.actionId
import com.example.matrixapp.view.activity.DrawerActivity


class PartnerFragment : Fragment() {

    private val binding: FragmentPartnerBinding by lazy { FragmentPartnerBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actionId = R.id.action_partnerFragment_to_pricingFragment
        applyClick()
    }
    private fun applyClick(){
        with(binding){
            btnOpenDrawer.setOnClickListener {
                (requireActivity() as DrawerActivity).openDrawer()
            }
            btnProfile.setOnClickListener {
                findNavController().navigate(R.id.action_partnerFragment_to_accountFragment)
            }
            shareLink.setOnClickListener {

            }
            materialButtonParticipate.setOnClickListener {

            }
            privacyTxt.setOnClickListener {
                findNavController().navigate(R.id.action_partnerFragment_to_privacyFragment)
            }
        }
    }
}