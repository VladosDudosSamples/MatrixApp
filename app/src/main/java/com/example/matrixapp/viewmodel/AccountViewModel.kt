package com.example.matrixapp.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.matrixapp.R
import com.example.matrixapp.app.App
import com.example.matrixapp.model.server.UserObs
import com.example.matrixapp.utils.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AccountViewModel : ViewModel() {
    private var premiumCode: String = "11"
    private val accountStatus = MutableLiveData(false)
    val statusOfAccount = accountStatus
    fun premiumActivation(context: Context, code: String) {
        if (code == premiumCode && premiumCode.isNotEmpty()) {
            accountStatus.value = true
        } else Toast.makeText(
            context,
            context.getString(R.string.activation_failed),
            Toast.LENGTH_SHORT
        ).show()
    }

    fun getUserInfo(context: Context) {
        val disp = App.dm.api.getUser("Bearer " + App.dm.getToken())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ u ->
                Log.d("User", u.login)
                Utils.user = UserObs(u.id, u.login)
            }, {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            })
    }
}