package com.example.matrixapp.app

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.matrixapp.R
import com.example.matrixapp.model.api.Api

class DataManager(private val baseContext: Context) {
    private lateinit var masterKey: MasterKey
    private lateinit var sharedPreferences: EncryptedSharedPreferences
    val api = Api.createApi()
    private val preferences = baseContext.getSharedPreferences("MatrixAppVPN", Context.MODE_PRIVATE)

    fun initEncryptedSharedPrefs(context: Context) {
        masterKey = MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()
        sharedPreferences = EncryptedSharedPreferences.create(
            context,
            "MatrixAppVPN",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        ) as EncryptedSharedPreferences
    }

    fun encryptToken(token: String) {
        sharedPreferences.edit().putString("token", token).apply()
    }

    fun decryptToken(): String {
        return sharedPreferences.getString("token", "") ?: ""
    }

    fun passOnBoarding() {
        preferences.edit().putBoolean(baseContext.getString(R.string.on_boarding_passed), true)
            .apply()
    }

    fun isOnBoardingPassed(): Boolean {
        return preferences.getBoolean(baseContext.getString(R.string.on_boarding_passed), false)
    }

    fun passLogin() {
        preferences.edit().putBoolean(baseContext.getString(R.string.login_passed), true).apply()
    }

    fun isLoginPassed(): Boolean {
        return preferences.getBoolean(baseContext.getString(R.string.login_passed), false)
    }

    fun saveSelectedLocation(location: String) =
        preferences.edit().putString("location", location).apply()

    fun getSelectedLocation(): String =
        preferences.getString("location", "No connection") ?: "No connection"
}