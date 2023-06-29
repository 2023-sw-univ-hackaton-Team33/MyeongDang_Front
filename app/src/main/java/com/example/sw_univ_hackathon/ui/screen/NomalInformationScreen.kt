package com.example.sw_univ_hackathon.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sw_univ_hackathon.ui.card.NormalInformationCard
import com.example.sw_univ_hackathon.ui.data.CardDto
import com.example.sw_univ_hackathon.ui.theme.MDGray


@Composable
fun NormalInformationScreen(cardData: CardDto) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // 회색 홀드
        Box(modifier = Modifier.fillMaxWidth().height(0.6.dp).background(MDGray).alpha(0.4f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(85.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "전화번호",
                color = Color.Black,
                fontSize = 15.sp,
                fontWeight = FontWeight(600),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = cardData.phone,
                color = Color.Black,
                fontSize = 15.sp,
                fontWeight = FontWeight(250),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )

        }

        // 회색 홀드
        Box(modifier = Modifier.fillMaxWidth().height(0.6.dp).background(MDGray).alpha(0.4f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(85.dp)
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "전화번호",
                color = Color.Black,
                fontSize = 15.sp,
                fontWeight = FontWeight(600),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = cardData.email,
                color = Color.Black,
                fontSize = 15.sp,
                fontWeight = FontWeight(250),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )

        }

        // 회색 홀드
        Box(modifier = Modifier.fillMaxWidth().height(0.6.dp).background(MDGray).alpha(0.4f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(85.dp)
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "직장명",
                color = Color.Black,
                fontSize = 15.sp,
                fontWeight = FontWeight(600),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = cardData.company,
                color = Color.Black,
                fontSize = 15.sp,
                fontWeight = FontWeight(250),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )

        }


    }
}