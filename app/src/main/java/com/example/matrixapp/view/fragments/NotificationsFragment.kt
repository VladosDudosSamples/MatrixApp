package com.example.matrixapp.view.fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.matrixapp.R
import com.example.matrixapp.databinding.DeleteNotificationsDialogBinding
import com.example.matrixapp.databinding.FragmentNotuficationsBinding
import com.example.matrixapp.utils.SwipeHelper
import com.example.matrixapp.view.activity.DrawerActivity
import com.example.matrixapp.view.adapter.NotificationAdapter
import com.example.matrixapp.viewmodel.NotificationViewModel

class NotificationsFragment : Fragment() {

    private val binding: FragmentNotuficationsBinding by lazy {
        FragmentNotuficationsBinding.inflate(layoutInflater)
    }
    private val viewModel: NotificationViewModel by viewModels()
    private lateinit var notificationAdapter: NotificationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNotificationAdapter()
        viewModel.getNotifications()
        setObservers()
        applyClick()
        applySwipeHelper()
    }

    private fun applySwipeHelper() {
        object : SwipeHelper(requireContext(), binding.rvNotificationsByDate, false) {
            override fun instantiateUnderlayButton(
                viewHolder: RecyclerView.ViewHolder?,
                underlayButtons: MutableList<UnderlayButton>?
            ) {
                underlayButtons?.add(UnderlayButton(

                    AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.delete_notification_button
                    )
                ) { pos: Int ->
                    viewModel.deleteItem(pos)
                    notificationAdapter.notifyItemRemoved(pos)
                })
            }
        }
    }

    private fun setObservers() {
        viewModel.notifications.observe(viewLifecycleOwner) {
            notificationAdapter.updateList(it)
        }
    }

    private fun applyClick() {
        with(binding) {
            btnOpenDrawer.setOnClickListener {
                (requireActivity() as DrawerActivity).openDrawer()
            }
            tvClearAllNotifications.setOnClickListener {
                showDeletionDialog()
            }
            tvSortOption.setOnClickListener {

            }
            btnProfile.setOnClickListener {
                findNavController()
            }
        }
    }

    private fun showSortMenu() {

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showDeletionDialog() {
        val dialogBinding: DeleteNotificationsDialogBinding by lazy {
            DeleteNotificationsDialogBinding.inflate(
                layoutInflater
            )
        }
        val dialog = Dialog(requireContext()).apply {
            setCancelable(false)
            setContentView(dialogBinding.root)
            window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        dialogBinding.tvCloseDialog.setOnClickListener {
            dialog.dismiss()
        }
        dialogBinding.tvClearAll.setOnClickListener {
            viewModel.clearNotifications()
            notificationAdapter.notifyDataSetChanged()
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun initNotificationAdapter() {
        notificationAdapter = NotificationAdapter(requireContext(), listOf())
        binding.rvNotificationsByDate.apply {
            adapter = notificationAdapter
        }
    }
}