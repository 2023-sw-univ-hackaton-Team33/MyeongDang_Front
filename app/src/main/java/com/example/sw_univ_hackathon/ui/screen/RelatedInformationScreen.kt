package com.example.sw_univ_hackathon.ui.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.sw_univ_hackathon.api.RetrofitBuilder
import com.example.sw_univ_hackathon.ui.card.RelatedChannelCard
import com.example.sw_univ_hackathon.ui.data.CardDto
import com.example.sw_univ_hackathon.ui.data.RelatedChannelResponse
import com.example.sw_univ_hackathon.util.ShowProgressBar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun RelatedInformationScreen(
    navHostController: NavHostController,
    cardData: CardDto
) {

    val context = LocalContext.current

    val responseBody = remember { mutableStateOf(RelatedChannelResponse()) }

    val isLoading = remember { mutableStateOf(true) }

    RetrofitBuilder.relatedChannelAPI
        .getUserData(cardData.userName, cardData.company)
        .enqueue(object : Callback<RelatedChannelResponse> {
            override fun onResponse(
                call: Call<RelatedChannelResponse>,
                response: Response<RelatedChannelResponse>
            ) {
                val res = response.body()
                if (res != null) {
                    responseBody.value = res
                    isLoading.value = false
                    Log.d("search", "불린다")

                }
            }

            override fun onFailure(call: Call<RelatedChannelResponse>, t: Throwable) {
                Toast.makeText(context, "서버와 연결하지 못했어요", Toast.LENGTH_SHORT).show()
            }

        })

    if (isLoading.value) {
        ShowProgressBar()
//        Handler().postDelayed({isLoading.value = false}, 500L)
    } else {
        val posts = responseBody.value.data

        if (posts != null) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 18.dp, vertical = 18.dp)
            ) {
                items(
                    items = posts,
                    key = { post -> post.id }
                ) { item ->
                    RelatedChannelCard(navHostController, item)
                }
            }

        }
    }

}
