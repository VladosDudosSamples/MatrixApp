package com.example.matrixapp.viewmodel


import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.matrixapp.model.Notification
import com.example.matrixapp.model.NotificationGroupItem
import com.example.matrixapp.model.NotificationType
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class NotificationViewModel(val app: Application) : AndroidViewModel(app) {

    var notifications = MutableLiveData<MutableList<NotificationGroupItem>>()

    private var notificationsList = mutableListOf(
        Notification(
            7,
            "Renewal notification",
            "On 22 May, a 10 subscription fee will be charged",
            LocalDate.parse("2023-06-20"),
            NotificationType.SUCCESS

        ),
        Notification(
            1,
            "Renewal notification",
            "On 22 May, a 10 subscription fee will be charged",
            LocalDate.parse("2023-05-20"),
            NotificationType.FAILURE
        ),
        Notification(
            1,
            "Renewal notification",
            "On 22 May, a 10 subscription fee will be charged",
            LocalDate.parse("2023-05-20"),
            NotificationType.FAILURE
        ),
        Notification(
            1,
            "Renewal notification",
            "On 22 May, a 10 subscription fee will be charged",
            LocalDate.parse("2023-05-20"),
            NotificationType.FAILURE
        ),

        Notification(
            4,
            "Renewal notification",
            "On 22 May, a 10 subscription fee will be charged",
            LocalDate.parse("2023-04-20"),
            NotificationType.PAYMENT

        ),
        )

    fun getNotifications() {
//        val distinct1: Map<LocalDate, MutableList<Notification>> = notificationsList.groupBy { it.date } as Map<LocalDate, MutableList<Notification>>
//        val pairs = mutableListOf<Pair<LocalDate, MutableList<Notification>>>()
//        distinct1.forEach { key, value ->
//            pairs.add(key to value)
//        }
        val distinct = notificationsList.distinctBy { it.date }
        val dates = mutableListOf<NotificationGroupItem>()
        distinct.forEach { item ->
            dates.add(
                NotificationGroupItem(
                    item.date,
                    (notificationsList).filter { it.date == item.date } as MutableList<Notification>
                )
            )
        }
        dates.sortByDescending { it.date }
        notifications.value = dates

        Log.d("dates", dates.toString())
    }


    fun clearNotifications() {
        notifications.value = mutableListOf()
    }

    fun deleteItem(item: Notification) {
        val notification = notifications.value!!.last { it.notifications.contains(item) }
        notification.notifications.remove(item)
        if (notification.notifications.isEmpty()) {
            notifications.value!!.remove(notification)
            notifications.value = notifications.value
            Log.d("SIZE", notifications.value!!.size.toString())
            Log.d("GROUP", notification.date.month.toString())
            Log.d("DELETED", "${notification.date} - ${notification.notifications.size}")
            Log.d("NOTIFICATIONS", notifications.value.toString())
        }
    }
}