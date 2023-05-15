package com.example.matrixapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.matrixapp.app.App
import com.example.matrixapp.model.ApiRepository
import com.example.matrixapp.model.ApiToken
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TokenViewModel(val app: Application) : AndroidViewModel(app){
    private val vpnToken = MutableLiveData<String>()
    val token = vpnToken
}