package com.example.matrixapp.view.fragments

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
                    //cheatView.visibility = View.VISIBLE
                    //showBottomSheet()

                } else {
                    imgSwitchBackground.setImageResource(R.drawable.switch_off_back)
                    roundImage.setImageResource(R.drawable.switch_round_off)
                    tvConnected.text = resources.getString(R.string.no_onnected)
                    tvConnectionTime.text = ""
                    //cheatView.visibility = View.GONE
                }
            }


        }
    }

//    private fun showBottomSheet() {
//        val dialogBinding = ConnectionStatisticsBottomSheetBinding.inflate(layoutInflater)
//        val dialog = BottomSheetDialog(requireContext())
//        val view = dialogBinding.root
//        dialog.setContentView(view)
//
//        val behavior = BottomSheetBehavior.from(view.parent as View)
//        behavior.addBottomSheetCallback(object: BottomSheetBehavior.BottomSheetCallback(){
//            override fun onStateChanged(bottomSheet: View, newState: Int) {
//                if(newState == 4){
//                   behavior.state = BottomSheetBehavior.STATE_HIDDEN
//                }
//                Toast.makeText(requireContext(), newState.toString(), Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onSlide(bottomSheet: View, slideOffset: Float) {
//                //Toast.makeText(requireContext(), "slide", Toast.LENGTH_SHORT).show()
//            }
//
//        })
//
//
//        behavior.peekHeight = (Resources.getSystem().displayMetrics.heightPixels * 0.07).toInt()
//        view.minimumHeight = (Resources.getSystem().displayMetrics.heightPixels * 0.21).toInt()
//        behavior.isFitToContents = true
//
//        dialog.show()
//    }
}