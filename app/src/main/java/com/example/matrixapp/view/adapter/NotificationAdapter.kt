package com.example.matrixapp.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.matrixapp.R
import com.example.matrixapp.databinding.NotificationCardBinding
import com.example.matrixapp.model.Notification
import com.example.matrixapp.model.NotificationType

class NotificationAdapter(
    private val context: Context,
    private var notifications: List<Notification>
) :
    RecyclerView.Adapter<NotificationAdapter.NotificationHolder>() {
    class NotificationHolder(val binding: NotificationCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationHolder {
        return NotificationHolder(
            NotificationCardBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = notifications.size

    override fun onBindViewHolder(holder: NotificationHolder, position: Int) {
        val item = notifications[position]
        with(holder.binding) {
            tvNotificationTitle.text = item.title
            tvNotificationContent.text = item.content
            tvNotificationDate.text = item.date
            when (item.notificationType) {
                NotificationType.FAILURE -> imageTypeOfNotification.setImageResource(R.drawable.failure_ic)
                NotificationType.SUCCESS -> imageTypeOfNotification.setImageResource(R.drawable.success_ic)
                NotificationType.PAYMENT -> imageTypeOfNotification.setImageResource(R.drawable.coin_ic)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<Notification>) {
        this.notifications = list
        notifyDataSetChanged()
    }
}