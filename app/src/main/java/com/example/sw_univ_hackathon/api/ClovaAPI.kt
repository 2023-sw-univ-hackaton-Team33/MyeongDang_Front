package com.example.sw_univ_hackathon.api

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

//interface ClovaAPI {
//
//    @Multipart
//    @POST("")
//    fun postCreate(
//        @Part("message") message: RequestBody,
//        @Part beforeImage: MultipartBody.Part?,
//        @Part afterImage: MultipartBody.Part?
//    ): Call<PostCreateRes>
//}

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