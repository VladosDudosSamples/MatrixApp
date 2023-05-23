package com.example.matrixapp.model

import java.time.LocalDate

data class NotificationGroupItem(
    val date: LocalDate,
    val notifications: MutableList<Notification>
)