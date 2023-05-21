package com.example.matrixapp.view.fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.matrixapp.databinding.DeleteNotificationsDialogBinding
import com.example.matrixapp.databinding.FragmentNotuficationsBinding
import com.example.matrixapp.model.Notification
import com.example.matrixapp.view.activity.DrawerActivity
import com.example.matrixapp.view.adapter.NotificationAdapter
import com.example.matrixapp.viewmodel.NotificationViewModel

@RequiresApi(Build.VERSION_CODES.O)
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
    private fun deleteItem(item: Notification) {
        viewModel.deleteItem(item)
        binding.rvNotificationsByDate.post{
            notificationAdapter.notifyDataSetChanged()
        }
        notificationAdapter.notifyDataSetChanged()
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
            //notificationAdapter.notifyDataSetChanged()
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun initNotificationAdapter() {
        notificationAdapter = NotificationAdapter(requireContext(), listOf()) {
            deleteItem(it)
        }
        binding.rvNotificationsByDate.apply {
            adapter = notificationAdapter
        }
    }
}