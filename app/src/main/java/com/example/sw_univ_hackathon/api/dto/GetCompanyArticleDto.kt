package com.example.sw_univ_hackathon.api.dto

data class GetCompanyArticleDto(
    var code: Int,
    var isSuccess: Boolean,
    var message: String,
    var result: ArrayList<CompanyArticleDto> = arrayListOf()
)


data class CompanyArticleDto(
    var companyName: String = "",
    var title: String = "",
    var keyWord: String = "",
    var summary: String = "",
    var url: String = ""
)
