package com.example.matrixapp.view.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.matrixapp.R
import com.example.matrixapp.app.App
import com.example.matrixapp.databinding.FragmentMatrixVpmBinding
import com.example.matrixapp.utils.Case.actionId
import com.example.matrixapp.view.activity.DrawerActivity
import com.example.matrixapp.viewmodel.MainService
import com.example.matrixapp.viewmodel.VpnViewModel
import com.sothree.slidinguppanel.SlidingUpPanelLayout


class MatrixVpmFragment : Fragment() {

    private val binding: FragmentMatrixVpmBinding by lazy {
        FragmentMatrixVpmBinding.inflate(layoutInflater)
    }
    private val viewModel: VpnViewModel by viewModels()

    private val TAG = "SocketServer"
    var connectivityManager: ConnectivityManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        applyCLick()
        setLocationText()
        viewModel.getKeyFromApi(requireContext())
        actionId = R.id.action_matrixVpmFragment_to_pricingFragment
    }

    private fun setLocationText() {
        binding.tvLocation.text = App.dm.getSelectedLocation()
    }

    override fun onResume() {
        super.onResume()
        setLocationText()
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
                    binding.panel.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
                    useProxy()
                } else {
                    imgSwitchBackground.setImageResource(R.drawable.switch_off_back)
                    roundImage.setImageResource(R.drawable.switch_round_off)
                    tvConnected.text = resources.getString(R.string.no_connected)
                    tvConnectionTime.text = ""
                    binding.panel.panelState = SlidingUpPanelLayout.PanelState.HIDDEN
                    stepProxy()
                }
            }
            btnProfile.setOnClickListener {
                findNavController().navigate(R.id.action_matrixVpmFragment_to_accountFragment)
            }
            imageConstraintLayout.setOnClickListener {
                findNavController().navigate(R.id.action_matrixVpmFragment_to_locationFragment)
            }
        }
    }

    private fun stepProxy() {
        toggleVpnService(false)
    }

    private fun useProxy() {
        val intent = MainService.prepare(requireContext())
        if (intent != null) {
            requireActivity().startActivityForResult(intent, 0)
        } else {
            toggleVpnService(true)
        }
    }
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        @Nullable data: Intent?
    ) {
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            toggleVpnService(true)
        } else {
            Toast.makeText(requireContext(), "Really!?", Toast.LENGTH_LONG).show()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
    private fun toggleVpnService(start: Boolean) {
        val intent = Intent(requireContext(), MainService::class.java)
        intent.action = if (start) {
            Log.d("TEST", "tun2socks start starting")
            MainService.ACTION_START
        } else MainService.ACTION_STOP
        ContextCompat.startForegroundService(requireContext(), intent)
    }
}