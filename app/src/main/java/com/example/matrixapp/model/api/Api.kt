package com.example.matrixapp.model.api

import com.example.matrixapp.model.ApiToken
import com.example.matrixapp.model.server.Key
import com.example.matrixapp.model.server.LoginPost
import com.example.matrixapp.model.server.RegisterObs
import com.example.matrixapp.model.server.RegisterPost
import com.example.matrixapp.model.server.RegistrationDevice
import com.example.matrixapp.model.server.Token
import com.example.matrixapp.model.server.UserObs
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface Api {
    @POST("register")
    fun register(@Body registerPost: RegisterPost) : Observable<RegisterObs>

    @POST("register_device_id")
    fun registerDevice(@Body registrationDevice: RegistrationDevice) : Observable<RegisterObs>
    @POST("login")
    fun login(@Body loginPost: LoginPost) : Observable<Token>

    @GET("user")
    fun getUser(@Header("Authorization") token: String) : Observable<UserObs>

    @GET("get_key")
    fun getKey(@Header("Authorization") token: String) : Observable<Key>

    @POST("refresh")
    fun refreshToken() : Observable<Token>

    @POST("logout")
    fun logout()

    companion object{
        fun createApi():Api{
            val gson = GsonBuilder()
                .setLenient()
                .create()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://admin.matrixx.website/api/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            return retrofit.create(Api::class.java)
        }
    }
}