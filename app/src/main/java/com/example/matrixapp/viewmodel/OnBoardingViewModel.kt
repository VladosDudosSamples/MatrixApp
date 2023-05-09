package com.example.matrixapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.matrixapp.R
import com.example.matrixapp.app.App
import com.example.matrixapp.app.DataManager
import com.example.matrixapp.model.OnBoardingItem
import java.util.*

class OnBoardingViewModel(private val app: Application) : AndroidViewModel(app) {

    var isLastPage = MutableLiveData(false)
    var isOnBoardingPassed = MutableLiveData(false)

    val items = LinkedList(
        listOf(
            OnBoardingItem(
                "Автоматическая смена сервера\nпри потере связи для\nбесперебойного подключения",
                R.drawable.onboarding_1
            ),
            OnBoardingItem(
                "Подключение\nв один клик",
                R.drawable.onboarding_2
            ),
            OnBoardingItem(
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
        App.dm.passOnBoarding()
        isOnBoardingPassed.value = true
    }
}