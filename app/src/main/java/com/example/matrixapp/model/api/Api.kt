package com.example.matrixapp.model.api

import com.example.matrixapp.model.ApiToken
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface Api {
    @GET(";lsdfhnvlirfejv")
    fun getToken() : Observable<ApiToken>

    companion object{
        fun createApi():Api{
            val gson = GsonBuilder()
                .setLenient()
                .create()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://kjtfujyfjyfu/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            return retrofit.create(Api::class.java)
        }
    }
}