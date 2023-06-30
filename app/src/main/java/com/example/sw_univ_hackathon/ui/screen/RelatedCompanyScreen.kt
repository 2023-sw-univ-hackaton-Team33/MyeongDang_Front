package com.example.sw_univ_hackathon.ui.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sw_univ_hackathon.api.RetrofitBuilder
import com.example.sw_univ_hackathon.api.dto.CompanyInfoDto
import com.example.sw_univ_hackathon.api.dto.GetCompanyDto
import com.example.sw_univ_hackathon.ui.card.RelatedChannelCard
import com.example.sw_univ_hackathon.ui.data.CardDto
import com.example.sw_univ_hackathon.ui.data.RelatedChannelResponse
import com.example.sw_univ_hackathon.ui.theme.MDGray
import com.example.sw_univ_hackathon.util.ShowProgressBar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun RelatedCompanyScreen(
    navHostController: NavHostController,
    cardData: CardDto
) {

    val context = LocalContext.current

    val responseBody = remember { mutableStateOf(CompanyInfoDto()) }

    val isLoading = remember { mutableStateOf(true) }

    RetrofitBuilder.imageAPI
        .getCompany(cardData.company)
        .enqueue(object : Callback<GetCompanyDto> {
            override fun onResponse(
                call: Call<GetCompanyDto>,
                response: Response<GetCompanyDto>
            ) {
                val res = response.body()
                if (res != null) {
                    responseBody.value = res.result
                    isLoading.value = false
                    Log.d("search", "불린다")

                }
            }

            override fun onFailure(call: Call<GetCompanyDto>, t: Throwable) {
                Toast.makeText(context, "서버와 연결하지 못했어요", Toast.LENGTH_SHORT).show()
            }

        })

    if (isLoading.value) {
        ShowProgressBar()
//        Handler().postDelayed({isLoading.value = false}, 500L)
    } else {
        val posts = responseBody.value

        if (posts != null) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding()
                    .verticalScroll(rememberScrollState())
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.6.dp)
                        .background(MDGray)
                        .alpha(0.4f)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "주요 제품",
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight(700),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = posts.mainProduct,
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight(250),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.6.dp)
                        .background(MDGray)
                        .alpha(0.4f)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "강점",
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight(700),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = posts.reputation,
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight(250),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.6.dp)
                        .background(MDGray)
                        .alpha(0.4f)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "매출",
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight(700),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = posts.sales,
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight(250),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.6.dp)
                        .background(MDGray)
                        .alpha(0.4f)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "자본",
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight(700),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = posts.capital,
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight(250),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.6.dp)
                        .background(MDGray)
                        .alpha(0.4f)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "사원 수",
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight(700),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = posts.employeeNum.toString() + "명",
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight(250),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.6.dp)
                        .background(MDGray)
                        .alpha(0.4f)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "위치",
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight(700),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = posts.location,
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight(250),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.6.dp)
                        .background(MDGray)
                        .alpha(0.4f)
                )

            }
        }
    }
}