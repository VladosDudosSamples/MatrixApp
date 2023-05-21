package com.example.matrixapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.matrixapp.model.PaymentItem
import com.example.matrixapp.model.PaymentListModel
import java.time.LocalDate
import java.util.Date

class PaymentViewModel : ViewModel() {
    private val baseList = listOf<PaymentItem>(
        PaymentItem(1,"Payment #1441", "Premium pricing1", 0, -20.00, LocalDate.parse("2023-05-23"),false),
        PaymentItem(2,"Payment #1442", "Premium pricing2", 1, 20.00, LocalDate.parse("2023-05-22"),false),
        PaymentItem(3,"Payment #1443", "Premium pricing3", 2, 20.00, LocalDate.parse("2023-05-21"),false),
        PaymentItem(4,"Payment #1444", "Premium pricing4", 0, -20.00, LocalDate.parse("2023-04-23"), false),
        PaymentItem(5,"Payment #1445", "Premium pricing5", 1, -20.00, LocalDate.parse("2023-04-22"), false),
        PaymentItem(6,"Payment #1446", "Premium pricing6", 2, 20.00, LocalDate.parse("2023-04-21"),false),
        PaymentItem(7,"Payment #1447", "Premium pricing7", 0, -20.00, LocalDate.parse("2023-03-22"), false),
        PaymentItem(8,"Payment #1448", "Premium pricing8", 1, -20.00, LocalDate.parse("2023-03-21"), false),
        PaymentItem(9,"Payment #1448", "Premium pricing8", 1, -20.00, LocalDate.parse("2023-03-21"), false),
        PaymentItem(10,"Payment #1448", "Premium pricing8", 1, -20.00, LocalDate.parse("2023-03-21"), false),
        PaymentItem(11,"Payment #1448", "Premium pricing8", 1, -20.00, LocalDate.parse("2023-03-21"), false),
        PaymentItem(12,"Payment #1448", "Premium pricing8", 1, -20.00, LocalDate.parse("2023-03-21"), false),
        PaymentItem(13,"Payment #10", "Premium pricing13", 0, -30.00, LocalDate.parse("2023-03-21"), false)
    )
    val currentListSize = MutableLiveData<Int>()
    val monthsLifeData = MutableLiveData<MutableList<PaymentListModel>>()
    fun getMonths(){
        val distinct = baseList.distinctBy { it.date.month }
        val months = mutableListOf<PaymentListModel>()
        distinct.forEach { item ->
            months.add(
                PaymentListModel(item.date, baseList.filter { it.date.month == item.date.month })
            )
        }
        monthsLifeData.value = months
        currentListSize.value = months.size
    }
}