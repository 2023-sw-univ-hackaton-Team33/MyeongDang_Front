package com.example.sw_univ_hackathon.api.dto

import org.w3c.dom.Text

data class ClovaDto(
    val images: List<NameCard> = listOf(),
)

data class NameCard(
    val nameCard: Result

)
data class Result(
    val result: ClovaData
)

data class ClovaData(
    val name: ArrayList<FinalData> = arrayListOf(),
    val company: ArrayList<FinalData> = arrayListOf(),
    val mobile: ArrayList<FinalData> = arrayListOf(),
    val email:ArrayList<FinalData> = arrayListOf()
)

data class FinalData(
    val text: String
)


