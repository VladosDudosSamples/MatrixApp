package com.example.matrixapp.view.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.matrixapp.R
import com.example.matrixapp.databinding.NotificationItemCardBinding
import com.example.matrixapp.model.Notification
import com.example.matrixapp.model.NotificationType
import java.time.format.DateTimeFormatter


class NotificationItemAdapter(
    private val context: Context,
    private var notifications: List<Notification>,
) :
    RecyclerView.Adapter<NotificationItemAdapter.NotificationItemHolder>() {

    class NotificationItemHolder(val binding: NotificationItemCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationItemHolder {
        return NotificationItemHolder(
            NotificationItemCardBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = notifications.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: NotificationItemHolder, position: Int) {
        val item = notifications[position]
        with(holder.binding) {
            tvNotificationTitle.text = item.title
            tvNotificationDate.ellipsize
            tvNotificationContent.text = item.content
            tvNotificationDate.text = DateTimeFormatter.ofPattern("dd MMM").format(item.date)
            when (item.notificationType) {
                NotificationType.FAILURE -> imageTypeOfNotification.setImageResource(R.drawable.failure_ic)
                NotificationType.SUCCESS -> imageTypeOfNotification.setImageResource(R.drawable.success_ic)
                NotificationType.PAYMENT -> imageTypeOfNotification.setImageResource(R.drawable.coin_ic)
            }
        }
    }
}