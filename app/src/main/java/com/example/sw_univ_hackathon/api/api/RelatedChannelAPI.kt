package com.example.sw_univ_hackathon.api.api

import com.example.sw_univ_hackathon.ui.data.RelatedChannelResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RelatedChannelAPI {

    @GET("Hello_Lambda_Python")
    fun getUserData(
        @Query("name") name: String,
        @Query("company") company: String,
    ): Call<RelatedChannelResponse>

}