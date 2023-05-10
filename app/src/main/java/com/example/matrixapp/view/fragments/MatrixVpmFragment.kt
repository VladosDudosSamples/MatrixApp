package com.example.matrixapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.matrixapp.R
import com.example.matrixapp.databinding.FragmentMatrixVpmBinding
import com.example.matrixapp.view.activity.DrawerActivity

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
                    cheatView.visibility = View.VISIBLE
                } else {
                    imgSwitchBackground.setImageResource(R.drawable.switch_off_back)
                    roundImage.setImageResource(R.drawable.switch_round_off)
                    tvConnected.text = resources.getString(R.string.no_onnected)
                    tvConnectionTime.text = ""
                    cheatView.visibility = View.GONE
                }
            }
        }
    }
}