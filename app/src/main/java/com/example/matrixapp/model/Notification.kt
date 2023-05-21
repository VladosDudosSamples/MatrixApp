package com.example.matrixapp.model

import java.time.LocalDate

data class Notification(
    val id: Int,
    val title: String,
    val content: String,
    val date: LocalDate,
    val notificationType: NotificationType
)
