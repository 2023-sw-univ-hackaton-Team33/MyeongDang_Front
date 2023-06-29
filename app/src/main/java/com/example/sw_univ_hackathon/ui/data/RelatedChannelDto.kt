package com.example.sw_univ_hackathon.ui.data

data class RelatedChannelResponse(
    val data: ArrayList<RelatedChannelDto> ?= null

)

data class RelatedChannelDto (
    val id: Int,
    val title: String,
    val link: String,
    val company: String
)