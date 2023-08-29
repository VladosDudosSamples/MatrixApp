package com.example.matrixapp.viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.matrixapp.app.App
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class VpnViewModel : ViewModel() {
    private val shadowSocksKey = MutableLiveData("")

    fun getKeyFromApi(context: Context) {
        println(App.dm.getToken())
        shadowSocksKey.value = App.dm.getKey()
        val disp = App.dm.api.getKey("Bearer " + App.dm.getToken())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("KEY", it.key)
                if (shadowSocksKey.value == "" || App.dm.getKey() != it.key) {
                    shadowSocksKey.value = it.key
                    App.dm.saveKey(it.key)
                }
            }, {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            })
    }
}