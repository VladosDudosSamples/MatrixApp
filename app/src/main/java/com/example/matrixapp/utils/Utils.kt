package com.example.matrixapp.utils

import android.content.Context

object Utils {
    fun Context.dpToPx(dp: Int): Int = (dp * resources.displayMetrics.density).toInt()
}