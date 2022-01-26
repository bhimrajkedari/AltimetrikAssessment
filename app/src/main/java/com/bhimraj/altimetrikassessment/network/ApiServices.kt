package com.bhimraj.altimetrikassessment.network

import com.bhimraj.altimetrikassessment.models.LaunchesItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {

    @GET("launches")
    fun getLaunches(): Call<List<LaunchesItem>>
}