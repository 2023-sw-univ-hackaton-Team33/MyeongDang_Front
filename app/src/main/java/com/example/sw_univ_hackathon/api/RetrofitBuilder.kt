package com.example.sw_univ_hackathon.api

import com.example.sw_univ_hackathon.ApplicationClass.Companion.retrofitClova
import com.example.sw_univ_hackathon.ApplicationClass.Companion.retrofitHuman
import com.example.sw_univ_hackathon.ApplicationClass.Companion.retrofitImages
import com.example.sw_univ_hackathon.api.api.ClovaAPI
import com.example.sw_univ_hackathon.api.api.InformationAPI
import com.example.sw_univ_hackathon.api.api.RelatedChannelAPI


object RetrofitBuilder {
    val imageAPI: InformationAPI = retrofitImages.create(InformationAPI::class.java)
    val relatedChannelAPI: RelatedChannelAPI = retrofitHuman.create(RelatedChannelAPI::class.java)
    val clovaDataAPI: ClovaAPI = retrofitClova.create(ClovaAPI::class.java)
}