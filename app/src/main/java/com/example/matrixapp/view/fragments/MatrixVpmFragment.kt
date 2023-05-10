package com.example.matrixapp.view.fragments

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.matrixapp.R
import com.example.matrixapp.databinding.ConnectionStatisticsBottomSheetBinding
import com.example.matrixapp.databinding.FragmentMatrixVpmBinding
import com.example.matrixapp.utils.OnSwipeTouchListener
import com.example.matrixapp.view.activity.DrawerActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

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
                    cheatView.visibility = View.VISIBLE

                } else {
                    imgSwitchBackground.setImageResource(R.drawable.switch_off_back)
                    roundImage.setImageResource(R.drawable.switch_round_off)
                    tvConnected.text = resources.getString(R.string.no_onnected)
                    tvConnectionTime.text = ""
                    cheatView.visibility = View.GONE
                }
            }

            cheatView.setOnTouchListener(object: OnSwipeTouchListener(requireContext()){
                override fun onSwipeUp() {
                    super.onSwipeUp()
                    showBottomSheet()
                }
            })
        }
    }

    private fun showBottomSheet() {
        val dialogBinding = ConnectionStatisticsBottomSheetBinding.inflate(layoutInflater)
        val dialog = BottomSheetDialog(requireContext())
        val view = dialogBinding.root
        dialog.setContentView(view)

        val behavior = BottomSheetBehavior.from(view.parent as View)
        behavior.state = BottomSheetBehavior.STATE_DRAGGING
//        behavior.peekHeight = (Resources.getSystem().displayMetrics.heightPixels * 0.07).toInt()
        //view.minimumHeight = (Resources.getSystem().displayMetrics.heightPixels * 21.12).toInt()
        behavior.isFitToContents = true

        dialog.show()
    }
}