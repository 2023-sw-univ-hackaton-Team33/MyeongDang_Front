package com.example.sw_univ_hackathon.api.api

import com.example.sw_univ_hackathon.api.dto.ImageResultDto
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ImageAPI {

    @Multipart
    @POST("/posts/images")
    fun imageUpload(
        @Part file: MultipartBody.Part?,
    ): Call<ImageResultDto>

}
