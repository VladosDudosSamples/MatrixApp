package com.example.matrixapp.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.matrixapp.model.Notification
import com.example.matrixapp.model.NotificationType
import java.time.LocalDate

class NewNotificationViewModel : ViewModel() {

    val notifications = MutableLiveData<MutableMap<LocalDate, MutableList<Notification>>>()

    @RequiresApi(Build.VERSION_CODES.O)
    private var notificationsList = mutableListOf(
        Notification(
            7,
            "Renewal notification",
            "On 20 June, a 10 subscription fee will be charged",
            LocalDate.parse("2023-06-20"),
            NotificationType.SUCCESS

        ),
        Notification(
            1,
            "Renewal notification",
            "On 20 May, a 10 subscription fee will be charged",
            LocalDate.parse("2023-05-20"),
            NotificationType.FAILURE
        ),
        Notification(
            1,
            "Renewal notification",
            "On 20 May, a 10 subscription fee will be charged",
            LocalDate.parse("2023-05-20"),
            NotificationType.FAILURE
        ),

        Notification(
            4,
            "Renewal notification",
            "On 20 April, a 10 subscription fee will be charged",
            LocalDate.parse("2023-04-20"),
            NotificationType.PAYMENT
        ),
    )

    fun getNotifications() {
        val map: MutableMap<LocalDate, MutableList<Notification>> = HashMap()
        for (notification in notificationsList) {
            map[notification.date] =
                notificationsList.filter { it.date == notification.date }.toMutableList()
        }
        notifications.value = map
        Log.d("notificationsMap:", map.toString())
    }

    fun clearNotifications() {
        notifications.value = mutableMapOf()
    }

    fun deleteItem(item: Notification) {
        val notificationGroupItem = notifications.value!!.values.find { it.contains(item) }
        notificationGroupItem?.remove(item)
//        if (notificationGroupItem!!.isEmpty()) {
//            notifications.value!!.values.remove(notificationGroupItem)
//            Log.d("SIZE", notifications.value!!.size.toString())
//            Log.d("NOTIFICATIONS", notifications.value.toString())
//        }
        notifications.value = notifications.value
        //notifications.value = notifications.value
    }
}