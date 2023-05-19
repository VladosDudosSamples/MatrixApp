package com.example.matrixapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.matrixapp.R
import com.example.matrixapp.databinding.FragmentSupportBinding
import com.example.matrixapp.view.activity.DrawerActivity
import com.example.matrixapp.view.adapter.FaqAdapter

class SupportFragment : Fragment() {

    private val binding: FragmentSupportBinding by lazy {
        FragmentSupportBinding.inflate(
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
        setAdapter()
    }

    private fun applyClick() {
        with(binding) {
            telegramCardView.setOnClickListener { }
            contactCardView.setOnClickListener { }
            mailCardView.setOnClickListener { }

            btnOpenDrawer.setOnClickListener {
                (requireActivity() as DrawerActivity).openDrawer()
            }
            btnProfile.setOnClickListener {
                findNavController().navigate(R.id.action_supportFragment_to_accountFragment)
            }
        }
    }

    private fun setAdapter() {
        with(binding) {
            val manager = LinearLayoutManager(requireContext())
            rvFaq.layoutManager = manager
            rvFaq.adapter = FaqAdapter(requireContext(), manager)
        }
    }
}