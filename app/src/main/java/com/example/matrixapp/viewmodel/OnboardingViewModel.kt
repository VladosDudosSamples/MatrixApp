package com.example.matrixapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.matrixapp.R
import com.example.matrixapp.model.OnboardingItem
import java.util.*

class OnboardingViewModel(private val app: Application) : AndroidViewModel(app) {

    var isLastPage = MutableLiveData(false)
    var isOnBoardingPassed = MutableLiveData(false)

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
                "Прямое подключение без\nхранения персональных данных" +
                        "и логов пользователей",
                R.drawable.onboarding_3
            ),
        )
    )


    fun setIsLastPage(isLast: Boolean) {
        isLastPage.value = isLast
    }

    fun setOnBoardingPassed() {
        isOnBoardingPassed.value = true
    }
}