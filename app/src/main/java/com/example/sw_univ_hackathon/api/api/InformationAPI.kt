package com.example.sw_univ_hackathon.api.api

import com.example.sw_univ_hackathon.api.dto.GetCompanyArticleDto
import com.example.sw_univ_hackathon.api.dto.GetCompanyDto
import com.example.sw_univ_hackathon.api.dto.ImageResultDto
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface InformationAPI {

    @Multipart
    @POST("/posts/images")
    fun imageUpload(
        @Part file: MultipartBody.Part?,
    ): Call<ImageResultDto>

    @GET("/company/{companyName}")
    fun getCompany(
        @Path("companyName") companyName: String
    ): Call<GetCompanyDto>

    @GET("/article/{companyName}")
    fun getCompanyArticle(
        @Path("companyName") companyName: String
    ): Call<GetCompanyArticleDto>


}
