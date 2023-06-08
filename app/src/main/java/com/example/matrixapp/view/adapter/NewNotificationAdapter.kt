package com.example.matrixapp.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.example.matrixapp.R
import com.example.matrixapp.databinding.NotificationCardBinding
import com.example.matrixapp.model.Notification
import com.example.matrixapp.utils.SwipeHelper
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class NewNotificationAdapter(
    private val context: Context,
    private var notifications: MutableMap<LocalDate, MutableList<Notification>>,
    val onClick: (Notification) -> Unit = {},
) : RecyclerView.Adapter<NewNotificationAdapter.NotificationHolder>() {

    var keyData: ArrayList<LocalDate>? = null

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

    override fun getItemCount(): Int {
        keyData = ArrayList(notifications.keys)
        return notifications.size
    }


    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(
        holder: NotificationHolder,
        position: Int
    ) {
        val item = keyData?.get(position)
        val items = notifications[item]?.let {
            it
        }
        val smallAdapter = items?.let { NotificationItemAdapter(context, it) }
        with(holder.binding) {
            tvDate.text = DateTimeFormatter.ofPattern("dd MMM").format(item)
            rvNotifications.adapter = smallAdapter

            items?.let {
                smallAdapter?.let { it1 ->
                    applySwipeHelper(
                        rvNotifications, it, root,
                        it1
                    )
                }
            }
        }
    }

    private fun applySwipeHelper(
        recycler: RecyclerView,
        items: MutableList<Notification>,
        textView: View,
        adapter: NotificationItemAdapter
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
                    adapter.notifyItemRemoved(pos)
                    if (items.isEmpty()) {
                        textView.updateLayoutParams<RecyclerView.LayoutParams> {
                            height = 0
                            setMargins(0, 0, 0, 0)
                        }
                    }
                })
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: MutableMap<LocalDate, MutableList<Notification>>) {
        if (list.isEmpty()) {
            notifyDataSetChanged()
        }
        this.notifications = list
    }
}