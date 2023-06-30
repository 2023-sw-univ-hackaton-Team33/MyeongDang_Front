package com.example.sw_univ_hackathon.api.api

import com.example.sw_univ_hackathon.api.dto.ClovaDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ClovaAPI {

    @Multipart
    @POST("name-card")
    fun getClova(
        @Part("message") message: RequestBody,
        @Part file: MultipartBody.Part?
    ): Call<ClovaDto>
}

data class Message (
    val version: String = "테스트 제목",
    val requestId: String = "0",
    val timestamp: Int = 0,
    val images: List<Images> = listOf(),
)

data class Images (
    val format: String ="jpg",
    val name: String,
)