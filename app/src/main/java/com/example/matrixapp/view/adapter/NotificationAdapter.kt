package com.example.matrixapp.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
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
    private var notifications: List<NotificationGroupItem>,
    val onClick: (Notification) -> Unit = {},
) : RecyclerView.Adapter<NotificationAdapter.NotificationHolder>() {
    class NotificationHolder(val binding: NotificationCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    private lateinit var adapter: NotificationItemAdapter

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
        adapter = NotificationItemAdapter(context, item.notifications)
        with(holder.binding) {
            tvDate.text = DateTimeFormatter.ofPattern("dd MMM").format(item.date)
            rvNotifications.adapter =
                adapter
            applySwipeHelper(rvNotifications, item.notifications)
        }
    }

    override fun getItemCount(): Int = notifications.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<NotificationGroupItem>) {
        this.notifications = list
        notifyDataSetChanged()
    }

    private fun applySwipeHelper(recycler: RecyclerView, items: List<Notification>) {
        object : SwipeHelper(context, recycler, false) {
            @SuppressLint("NotifyDataSetChanged")
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
                    Toast.makeText(context, item.id.toString(), Toast.LENGTH_SHORT).show()
                    onClick(item)
                    notifyItemRemoved(item.id)
                    recycler.adapter!!.notifyItemRemoved(item.id)
                    notifyDataSetChanged()
                    adapter.notifyDataSetChanged()
                    adapter.notifyItemRemoved(pos)
                })
            }
        }
    }

}