package com.example.matrixapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.matrixapp.model.Notification
import com.example.matrixapp.model.NotificationType

class NotificationViewModel : ViewModel() {

    var notifications = MutableLiveData<List<Notification>>()

    private val notificationsList = mutableListOf(
        Notification(
            "Renewal notification",
            "On 22 May, a 10",
            "12 May",
            NotificationType.PAYMENT
        ),
        Notification(
            "The subscription has been extended!",
            "On 22 May, a 10 subscription fee will be charged",
            "12 May",
            NotificationType.SUCCESS
        ),
        Notification(
            "Payment failed",
            "On 22 May, a 10 subscription fee will be charged",
            "12 May",
            NotificationType.FAILURE
        ),
        Notification(
            "Renewal notification",
            "On 22 May, a 10 subscription fee will be charged",
            "11 May",
            NotificationType.PAYMENT
        ),
        Notification(
            "The subscription has been extended!",
            "On 22 May, a 10 subscription fee will be charged",
            "11 May",
            NotificationType.SUCCESS
        ),
        Notification(
            "Payment failed",
            "On 22 May, a 10 subscription fee will be charged",
            "11 May",
            NotificationType.FAILURE
        ),
        Notification(
            "Renewal notification",
            "On 22 May, a 10 subscription fee will be charged",
            "10 May",
            NotificationType.PAYMENT
        ),
        Notification(
            "The subscription has been extended!",
            "On 22 May, a 10 subscription fee will be charged",
            "10 May",
            NotificationType.SUCCESS
        ),
        Notification(
            "Payment failed",
            "On 22 May, a 10 subscription fee will be charged",
            "10 May",
            NotificationType.FAILURE
        ),
        )


    fun getNotifications(){
        notifications.value = notificationsList
    }

    fun clearNotifications(){
        notifications.value = listOf()
    }

    fun deleteItem(position: Int){
        notificationsList.removeAt(position)
    }
}