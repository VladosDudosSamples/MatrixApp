package com.example.matrixapp.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.matrixapp.databinding.FragmentLocationBinding
import com.example.matrixapp.view.adapter.LocationAdapter
import com.example.matrixapp.viewmodel.LocationViewModel

class LocationFragment : Fragment() {

    private val viewModel: LocationViewModel by viewModels()
    private val binding: FragmentLocationBinding by lazy {
        FragmentLocationBinding.inflate(layoutInflater)
    }
    private lateinit var privateLocationsAdapter: LocationAdapter
    private lateinit var freeLocationsAdapter: LocationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPrivateLocationsAdapter()
        viewModel.getLocations()
        setObservers()
        applyClicks()
    }

    private fun setObservers() {
        viewModel.privateLocations.observe(viewLifecycleOwner) {
            privateLocationsAdapter.updateList(it)
        }
        viewModel.freeLocations.observe(viewLifecycleOwner) {
            freeLocationsAdapter.updateList(it)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun onItemClick(location: com.example.matrixapp.model.Location) {
        viewModel.privateLocations.value!!.forEach {
            if (it != location)
                it.isSelected = false
        }
        viewModel.freeLocations.value!!.forEach {
            if (it != location)
                it.isSelected = false
        }
        binding.rvFreeLocations.post {
            freeLocationsAdapter.notifyDataSetChanged()
        }
        binding.rvPremiumLocations.post {
            privateLocationsAdapter.notifyDataSetChanged()
        }
        viewModel.selectLocation(location)
    }

    private fun initPrivateLocationsAdapter() {
        privateLocationsAdapter =
            LocationAdapter(requireContext(), listOf(), true, onCardClickListener = {
                onItemClick(it)
            })
        freeLocationsAdapter =
            LocationAdapter(requireContext(), listOf(), false, onCardClickListener = {
                onItemClick(it)
            })
        with(binding.rvPremiumLocations) {
            adapter = privateLocationsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        with(binding.rvFreeLocations) {
            adapter = freeLocationsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun applyClicks(){
        binding.btnBack.setOnClickListener{
            findNavController().popBackStack()
        }
    }
}