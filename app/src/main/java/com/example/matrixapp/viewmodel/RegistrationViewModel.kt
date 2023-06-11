package com.example.matrixapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.matrixapp.app.App
import com.example.matrixapp.model.server.RegisterPost
import com.example.matrixapp.model.server.RegistrationDevice
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RegistrationViewModel : ViewModel() {

    fun registration(registerPost: RegisterPost){
        val disp = App.dm.api.register(registerPost)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("REGISTRATION", it.login)
            }, {
                Log.d("REGISTRATION", it.message.toString())
            })
    }
    fun registrationDevice(registrationDevice: RegistrationDevice){
        val disp = App.dm.api.register(registrationDevice).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("REGISTRATION", it.login)
            }, {
                Log.d("REGISTRATION", it.message.toString())
            })
    }
}