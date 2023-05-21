package com.example.matrixapp.viewmodel


import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.matrixapp.model.Notification
import com.example.matrixapp.model.NotificationGroupItem
import com.example.matrixapp.model.NotificationType
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class NotificationViewModel : ViewModel() {

    var notifications = MutableLiveData<List<NotificationGroupItem>>()

    private val notificationsList = mutableListOf(
        Notification(
            1,
            "Renewal notification",
            "On 22 May, a 10 subscription fee will be charged",
            LocalDate.parse("2023-05-20"),
            NotificationType.PAYMENT
        ),
        Notification(
            2,
            "The subscription has been extended!",
            "On 22 May, a 10 subscription fee will be charged",
            LocalDate.parse("2023-05-20"),
            NotificationType.SUCCESS
        ),
        Notification(
            3,
            "Payment failed",
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
        Notification(
            5,
            "The subscription has been extended!",
            "On 22 May, a 10 subscription fee will be charged",
            LocalDate.parse("2023-04-20"),
            NotificationType.SUCCESS
        ),

        Notification(
            6,
            "Payment failed",
            "On 22 May, a 10 subscription fee will be charged",
            LocalDate.parse("2023-04-20"),
            NotificationType.FAILURE

        ),
        Notification(
            7,
            "Renewal notification",
            "On 22 May, a 10 subscription fee will be charged",
            LocalDate.parse("2023-03-20"),
            NotificationType.PAYMENT

        ),
        Notification(
            8,
            "The subscription has been extended!",
            "On 22 May, a 10 subscription fee will be charged",
            LocalDate.parse("2023-03-20"),
            NotificationType.SUCCESS

        ),

        Notification(
            9,
            "Payment failed",
            "On 22 May, a 10 subscription fee will be charged",
            LocalDate.parse("2023-03-20"),
            NotificationType.FAILURE
        ),
    )

    fun getNotifications() {
        val distinct = notificationsList.distinctBy { it.date }
        val dates = mutableListOf<NotificationGroupItem>()
        distinct.forEach { item ->
            dates.add(
                NotificationGroupItem(
                    item.date,
                    (notificationsList).filter { it.date == item.date }
                )
            )
        }
        notifications.value = dates
        Log.d("dates", dates.toString())
    }

    fun clearNotifications() {
        notifications.value = listOf()
    }

    fun deleteItem(item: Notification) {
        notificationsList.remove(item)
        getNotifications()
    }
}