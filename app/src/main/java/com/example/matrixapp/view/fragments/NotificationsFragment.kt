package com.example.matrixapp.view.fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.matrixapp.R
import com.example.matrixapp.databinding.DeleteNotificationsDialogBinding
import com.example.matrixapp.databinding.FragmentNotuficationsBinding
import com.example.matrixapp.model.Notification
import com.example.matrixapp.utils.Case.actionId
import com.example.matrixapp.view.activity.DrawerActivity
import com.example.matrixapp.view.adapter.NewNotificationAdapter
import com.example.matrixapp.viewmodel.NewNotificationViewModel


@RequiresApi(Build.VERSION_CODES.O)
class NotificationsFragment : Fragment() {

    private val binding: FragmentNotuficationsBinding by lazy {
        FragmentNotuficationsBinding.inflate(layoutInflater)
    }
    private val newNotificationViewModel: NewNotificationViewModel by viewModels()
    private lateinit var newNotificationAdapter: NewNotificationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actionId = R.id.action_notificationsFragment_to_pricingFragment
        initNotificationAdapter()
        newNotificationViewModel.getNotifications()
        setObservers()
        applyClick()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setObservers() {
        newNotificationViewModel.notifications.observe(viewLifecycleOwner) {
            newNotificationAdapter.updateList(it)
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
                showSortMenu()
            }
            btnProfile.setOnClickListener {
                findNavController().navigate(R.id.action_notificationsFragment_to_accountFragment)
            }
        }
    }

    private fun showSortMenu() {
        val wrapper: Context = ContextThemeWrapper(requireContext(), R.style.popupMenuStyle)
        val popup = PopupMenu(wrapper, binding.textView8)

        popup.inflate(R.menu.sorting_menu)
        popup.setOnMenuItemClickListener {
            binding.tvSortOption.text = it.title.toString()
            true
        }
        popup.show()
    }

    private fun deleteItem(item: Notification) {
        newNotificationViewModel.deleteItem(item)
    }

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
            newNotificationViewModel.clearNotifications()
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun initNotificationAdapter() {
        newNotificationAdapter =
            NewNotificationAdapter(requireContext(), mutableMapOf(), onClick = {
                deleteItem(it)
            })
        binding.rvNotificationsByDate.apply {
            adapter = newNotificationAdapter
        }
    }
}