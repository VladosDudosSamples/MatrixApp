package com.example.matrixapp.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.matrixapp.R
import com.example.matrixapp.app.App
import com.example.matrixapp.model.server.RegisterPost
import com.example.matrixapp.model.server.RegistrationDevice
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RegistrationViewModel : ViewModel() {

    val successResponse = MutableLiveData(false)
    fun registration(registerPost: RegisterPost, context: Context){
        val disp = App.dm.api.register(registerPost)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("REGISTRATION", "${it.login} work1")
                successResponse.value = true
            }, {
                Toast.makeText(context, context.getString(R.string.smth_wrong), Toast.LENGTH_SHORT).show()
            })
    }
    fun registrationDevice(registrationDevice: RegistrationDevice, context: Context){
        val disp = App.dm.api.registerDevice(registrationDevice).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("REGISTRATION", "${it.login} work2")
            }, {
                Toast.makeText(context, it.message.toString(), Toast.LENGTH_SHORT).show()
            })
    }
}