package com.example.sw_univ_hackathon.api

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ImageAPI {

    @Multipart
    @POST("/posts/images")
    fun imageUpload(
        @Part file: MultipartBody.Part?,
    ): Call<ImageResult>

}

data class ImageResult(
    val url: String
)