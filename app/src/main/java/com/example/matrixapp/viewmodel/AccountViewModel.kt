package com.example.matrixapp.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.matrixapp.R

class AccountViewModel : ViewModel() {
    private var premiumCode: String = "11"
    private val accountStatus = MutableLiveData(false)
    val statusOfAccount = accountStatus

    fun premiumActivation(context: Context, code: String){
        if (code == premiumCode && premiumCode.isNotEmpty()){
            accountStatus.value = true
        }
        else Toast.makeText(context, context.getString(R.string.activation_failed), Toast.LENGTH_SHORT).show()
    }
}