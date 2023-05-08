package com.example.matrixapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.matrixapp.R
import com.example.matrixapp.model.OnboardingItem
import java.util.LinkedList

class OnboardingViewModel(val app: Application) : AndroidViewModel(app) {

    val items = LinkedList(
        listOf(
            OnboardingItem(
                "Автоматическая смена сервера\nпри потере связи для\nбесперебойного подключения",
                R.drawable.onboarding_1
            ),
            OnboardingItem(
                "Подключение\nв один клик",
                R.drawable.onboarding_2
            ),
            OnboardingItem(
                "Прямое подключение без хранения персональных данных \n" +
                        "и логов пользователей",
                R.drawable.onboarding_3
            ),

        )
    )

    var isLastPage = MutableLiveData(false)

    var currentText = app.resources.getString(R.string.skip)

    fun nextPage(): OnboardingItem {
        val removedItem = items.removeFirst()
        if (items.size == 0) {
            isLastPage.value = true
            currentText = app.resources.getString(R.string.go_start)
        }
        return removedItem
    }
}