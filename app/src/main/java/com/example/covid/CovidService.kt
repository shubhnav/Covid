package com.example.covid

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET


interface CovidService {

    @GET("dayone/country/india/status/confirmed")
    fun india(): Single<List<Country>>
}