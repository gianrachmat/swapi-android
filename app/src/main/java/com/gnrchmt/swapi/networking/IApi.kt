package com.gnrchmt.swapi.networking

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface IApi {
    @GET
    fun getSwapi(@Url url: String): Call<String>
}