package com.example.matrixapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.matrixapp.databinding.FragmentPrivacyBinding
import com.example.matrixapp.view.adapters.PrivacyAdapter
import com.example.matrixapp.viewmodel.PrivacyViewModel

class PrivacyFragment : Fragment() {

    private val binding: FragmentPrivacyBinding by lazy { FragmentPrivacyBinding.inflate(layoutInflater) }
    private val privacyViewModel: PrivacyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerAdapter()
    }
    private fun setRecyclerAdapter(){
        binding.privacyRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.privacyRecycler.adapter = PrivacyAdapter(requireContext(), privacyViewModel.getPrivacyRules())
    }
}