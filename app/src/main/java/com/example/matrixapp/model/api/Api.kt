package com.example.matrixapp.model.api

import com.example.matrixapp.model.ApiToken
import com.example.matrixapp.model.server.RegisterObs
import com.example.matrixapp.model.server.RegisterPost
import com.example.matrixapp.model.server.RegistrationDevice
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {
    @POST("api/register")
    fun register(@Body registerPost: RegisterPost) : Observable<RegisterObs>

    @POST("api/register_device_id")
    fun register(@Body registrationDevice: RegistrationDevice) : Observable<RegisterObs>

    companion object{
        fun createApi():Api{
            val gson = GsonBuilder()
                .setLenient()
                .create()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://admin.matrixx.website/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            return retrofit.create(Api::class.java)
        }
    }
}