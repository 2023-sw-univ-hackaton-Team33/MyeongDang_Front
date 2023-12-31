package com.example.sw_univ_hackathon.ui.data

import com.example.sw_univ_hackathon.R


enum class BusinessCard(
    val userName: String,
    val phone: String,
    val email: String,
    val company: String,
    val image: Int
) {
    CARD1("허재혁", "010-4738-1564", "leo@leolap.com", "리오랩", R.drawable.ic_card12),
    CARD2("박민호", "010-5042-7952", "kenny.park@goorm.io", "구름", R.drawable.ic_card14),
    CARD3("강동호", "010-6590-3302", "teddy.ryan@kakaostyle.com", "카카오 스타일", R.drawable.ic_card10)
//    FOOD("음식", "", "", R.drawable.ic_food)
}