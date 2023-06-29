package com.example.sw_univ_hackathon.ui.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sw_univ_hackathon.R
import com.example.sw_univ_hackathon.ui.data.RelatedChannelDto
import com.example.sw_univ_hackathon.ui.route.NAV_ROUTE
import com.example.sw_univ_hackathon.util.bounceClick


@Composable
fun RelatedChannelCard(
    navController: NavHostController,
    data: RelatedChannelDto
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .bounceClick {
                navController.navigate(NAV_ROUTE.WEBVIEW.routeName+ "/" +data.link)
            },
        verticalAlignment = Alignment.CenterVertically
    ){
        Column(
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(
                text = data.title,
                color = Color.Black,
                fontSize = 15.sp,
                fontWeight = FontWeight(700),
                modifier = Modifier
                    .fillMaxWidth(),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = data.company,
                color = Color(0xFF727272),
                fontSize = 13.sp,
                fontWeight = FontWeight(300),
                modifier = Modifier
                    .fillMaxWidth(),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.ic_chevron_right),
            contentDescription = null,
            modifier = Modifier
                .bounceClick {

                }
        )

    }
}