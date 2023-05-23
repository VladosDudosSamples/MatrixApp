package com.example.matrixapp.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.example.matrixapp.R
import com.example.matrixapp.databinding.NotificationCardBinding
import com.example.matrixapp.model.Notification
import com.example.matrixapp.model.NotificationGroupItem
import com.example.matrixapp.utils.SwipeHelper
import java.time.format.DateTimeFormatter

class NotificationAdapter(
    private val context: Context,
    private var notifications: MutableList<NotificationGroupItem>,
    val onClick: (Notification) -> Unit = {},
) : RecyclerView.Adapter<NotificationAdapter.NotificationHolder>() {
    class NotificationHolder(val binding: NotificationCardBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotificationHolder {
        return NotificationHolder(
            NotificationCardBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(
        holder: NotificationHolder,
        position: Int
    ) {
        val item = notifications[position]
        with(holder.binding) {
            tvDate.text = DateTimeFormatter.ofPattern("dd MMM").format(item.date)
            rvNotifications.adapter =
                NotificationItemAdapter(context, item.notifications)
            applySwipeHelper(rvNotifications, item.notifications, position)
        }
    }

    override fun getItemCount(): Int = notifications.size


    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<NotificationGroupItem>) {
        this.notifications = list as MutableList<NotificationGroupItem>
    }

    private fun applySwipeHelper(
        recycler: RecyclerView,
        items: MutableList<Notification>,
        position: Int,
    ) {
        object : SwipeHelper(context, recycler, false) {
            override fun instantiateUnderlayButton(
                viewHolder: RecyclerView.ViewHolder?,
                underlayButtons: MutableList<UnderlayButton>?
            ) {
                underlayButtons?.add(UnderlayButton(
                    AppCompatResources.getDrawable(
                        context,
                        R.drawable.delete_notification_button
                    )
                ) { pos: Int ->
                    val item = items[pos]
                    onClick(item)
                    recycler.adapter!!.notifyItemRemoved(pos)
                    updateList(notifications)
                    if (items.isEmpty()) {
                        notifyItemRemoved(position)
                    }
                })
            }
        }
    }
}