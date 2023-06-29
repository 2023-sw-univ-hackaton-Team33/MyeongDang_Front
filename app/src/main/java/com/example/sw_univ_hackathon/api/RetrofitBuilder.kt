package com.example.sw_univ_hackathon.api

import com.example.sw_univ_hackathon.ApplicationClass.Companion.retrofitImages


object RetrofitBuilder {
    val imageAPI: ImageAPI = retrofitImages.create(ImageAPI::class.java)

}