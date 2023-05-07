package com.example.matrixapp.model

import com.example.matrixapp.app.App
import io.reactivex.Observable

class ApiRepository {
    fun getToken() : Observable<ApiToken> {
        return App.dm.api.getToken()
    }
}