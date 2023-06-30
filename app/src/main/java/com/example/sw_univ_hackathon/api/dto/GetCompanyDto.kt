package com.example.sw_univ_hackathon.api.dto

data class GetCompanyDto(
    var code: Int,
    var isSuccess: Boolean,
    var message: String,
    var result: CompanyInfoDto = CompanyInfoDto()
)


data class CompanyInfoDto(
    var companyName : String = "",
    var mainProduct : String = "",
    var sales : String = "",
    var employeeNum : Int = 0,
    var reputation : String = "",
    var capital : String = "",
    var location : String = ""
)
