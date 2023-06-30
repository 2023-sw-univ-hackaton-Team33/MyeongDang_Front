package com.example.sw_univ_hackathon.api

import com.example.sw_univ_hackathon.ApplicationClass.Companion.retrofitClova
import com.example.sw_univ_hackathon.ApplicationClass.Companion.retrofitHuman
import com.example.sw_univ_hackathon.ApplicationClass.Companion.retrofitImages
import com.example.sw_univ_hackathon.api.api.ClovaAPI
import com.example.sw_univ_hackathon.api.api.ImageAPI
import com.example.sw_univ_hackathon.api.api.RelatedChannelAPI


object RetrofitBuilder {
    val imageAPI: ImageAPI = retrofitImages.create(ImageAPI::class.java)
    val relatedChannelAPI: RelatedChannelAPI = retrofitHuman.create(RelatedChannelAPI::class.java)
    val clovaDataAPI: ClovaAPI = retrofitClova.create(ClovaAPI::class.java)
}