package com.example.matrixapp.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.matrixapp.R
import com.example.matrixapp.app.App
import com.example.matrixapp.model.server.LoginPost
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlin.math.log

class LoginViewModel : ViewModel() {

    val successResponse = MutableLiveData(false)

    fun login(loginPost: LoginPost, context: Context){
        val disp = App.dm.api.login(loginPost)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("LOGIN", it.token)
                App.dm.saveToken(it.token)
                successResponse.value = true
            }, {
                Toast.makeText(context, context.getString(R.string.smth_wrong), Toast.LENGTH_SHORT).show()
            })
    }
}