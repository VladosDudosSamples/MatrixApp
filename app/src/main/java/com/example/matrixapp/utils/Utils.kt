package com.example.matrixapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.matrixapp.model.server.UserObs

object Utils {
    fun Context.dpToPx(dp: Int): Int = (dp * resources.displayMetrics.density).toInt()
    var user = UserObs(0, "")
}