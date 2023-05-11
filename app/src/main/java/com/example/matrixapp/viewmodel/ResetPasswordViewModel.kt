package com.example.matrixapp.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResetPasswordViewModel : ViewModel() {
    var isEmailEntered = MutableLiveData(false)
}