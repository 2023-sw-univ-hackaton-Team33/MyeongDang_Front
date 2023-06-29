package com.example.sw_univ_hackathon.ui.card

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sw_univ_hackathon.ui.data.CardDto


@Composable
fun NormalInformationCard(
    cardData: CardDto
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
        text = "",
        color = Color.Black,
        fontSize = 15.sp,
        fontWeight = FontWeight(500),
        modifier = Modifier
            .align(Alignment.CenterVertically)
            .padding(end = 15.dp)
            .weight(1f) // 추가: 남은 공간을   가득 채우도록 가중치 설정
            .wrapContentWidth(Alignment.CenterHorizontally) // 추가: 가운데 정렬
    )

    }


}