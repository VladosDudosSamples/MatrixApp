package com.example.matrixapp.model

import java.time.LocalDate

data class PaymentListModel(
    var month: LocalDate,
    var payments: List<PaymentItem>
)
