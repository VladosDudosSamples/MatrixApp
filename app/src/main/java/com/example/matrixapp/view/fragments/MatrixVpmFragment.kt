package com.example.matrixapp.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.matrixapp.R
import com.example.matrixapp.databinding.FragmentMatrixVpmBinding
import com.example.matrixapp.view.activity.DrawerActivity
import com.sothree.slidinguppanel.SlidingUpPanelLayout

class MatrixVpmFragment : Fragment() {

    private val binding: FragmentMatrixVpmBinding by lazy {
        FragmentMatrixVpmBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        applyCLick()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun applyCLick() {
        with(binding) {
            btnOpenDrawer.setOnClickListener {
                (requireActivity() as DrawerActivity).openDrawer()
            }
            switchOnOff.setOnCheckedChangeListener { _, checked ->
                if (checked) {
                    imgSwitchBackground.setImageResource(R.drawable.switch_on_back)
                    roundImage.setImageResource(R.drawable.switch_round_on)
                    tvConnected.text = resources.getString(R.string.connected)
                    tvConnectionTime.text = resources.getString(R.string.time_sample)
                    binding.panel.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED;

                } else {
                    imgSwitchBackground.setImageResource(R.drawable.switch_off_back)
                    roundImage.setImageResource(R.drawable.switch_round_off)
                    tvConnected.text = resources.getString(R.string.no_connected)
                    tvConnectionTime.text = ""
                    binding.panel.panelState = SlidingUpPanelLayout.PanelState.HIDDEN;
                }
            }
            btnProfile.setOnClickListener{
                findNavController().navigate(R.id.action_matrixVpmFragment_to_accountFragment)
            }
        }
    }

}