package com.example.matrixapp.app

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.matrixapp.model.api.Api

class DataManager(private val baseContext: Context) {
    private lateinit var masterKey: MasterKey
    private lateinit var sharedPreferences: EncryptedSharedPreferences
    val api = Api.createApi()

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
}