package com.example.matrixapp.model

import java.time.LocalDate

data class PaymentItem(
    var title : String,
    var description: String,
    var type: Int,
    var amount: Double,
    var date: LocalDate,
    var isActive: Boolean
)
