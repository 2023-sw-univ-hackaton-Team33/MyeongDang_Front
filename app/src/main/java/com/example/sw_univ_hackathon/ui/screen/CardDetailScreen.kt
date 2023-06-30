package com.example.sw_univ_hackathon.ui.screen

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.sw_univ_hackathon.R
import com.example.sw_univ_hackathon.ui.data.BusinessCard
import com.example.sw_univ_hackathon.ui.data.CardDto
import com.example.sw_univ_hackathon.ui.theme.MDGray
import com.example.sw_univ_hackathon.ui.theme.MDPoint
import com.example.sw_univ_hackathon.util.ModalBottomSheet
import com.example.sw_univ_hackathon.util.ModalBottomSheetItem
import com.example.sw_univ_hackathon.util.bounceClick
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun CardDetailScreen(
    navHostController: NavHostController,
    cardKey: String
) {
    Log.d("key-value", cardKey)
    val context = LocalContext.current.applicationContext
    val pagerState = rememberPagerState()

    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()
    lateinit var cardData :CardDto
    val showModalSheet = rememberSaveable { mutableStateOf(false) }
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    if(cardKey == "a"){
        cardData = CardDto(
            BusinessCard.CARD2.userName,
            BusinessCard.CARD2.phone,
            BusinessCard.CARD2.email,
            BusinessCard.CARD2.company,
            BusinessCard.CARD2.image,
        )
    }
    else if(cardKey == "b"){
        cardData = CardDto(
            BusinessCard.CARD3.userName,
            BusinessCard.CARD3.phone,
            BusinessCard.CARD3.email,
            BusinessCard.CARD3.company,
            BusinessCard.CARD3.image,
        )
    }
    else {
        cardData = CardDto(
            BusinessCard.CARD1.userName,
            BusinessCard.CARD1.phone,
            BusinessCard.CARD1.email,
            BusinessCard.CARD1.company,
            BusinessCard.CARD1.image,
        )
    }

    ModalBottomSheet(
        title = "",
        bottomSheetState = bottomSheetState,
        sheetScreen = {
            ModalBottomSheetItem(text = "SW공동 헤커톤 멘토", trailing = true, modifier = Modifier.bounceClick {
                scope.launch {
                    bottomSheetState.hide()
                }
            })
            ModalBottomSheetItem(text = "독서모임", trailing = true, modifier = Modifier.bounceClick {
                scope.launch {
                    bottomSheetState.hide()
                }
            })
            ModalBottomSheetItem(text = "두산그룹", trailing = true, modifier = Modifier.bounceClick {
                scope.launch {
                    bottomSheetState.hide()
                }
            })
            ModalBottomSheetItem(text = "거래처", trailing = true, modifier = Modifier.bounceClick {
                scope.launch {
                    bottomSheetState.hide()
                }
            })
        },
        uiScreen = {
            BackHandler(enabled = bottomSheetState.isVisible) {
                scope.launch {
                    bottomSheetState.hide()
                }
            }


            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(top = 20.dp, bottom = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.ic_chevron_left),
                        contentDescription = null,
                        modifier = Modifier
                            .bounceClick {

                            }
                            .padding(start=20.dp)
                    )
                    Text(
                        text = "상세보기",
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight(500),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(end = 15.dp)
                            .weight(1f) // 추가: 남은 공간을   가득 채우도록 가중치 설정
                            .wrapContentWidth(Alignment.CenterHorizontally) // 추가: 가운데 정렬
                    )
                }
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp)
                        .padding(20.dp, 10.dp),
                    contentScale = ContentScale.Crop,
                    painter = rememberImagePainter(cardData.image),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    Modifier
                        .wrapContentHeight()
                        .padding(horizontal = 18.dp, vertical = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "기본정보",
                        fontSize = 18.sp,
                        color =
                        if (pagerState.currentPage == 0) MDPoint
                        else MDGray,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .bounceClick {
                                scope.launch {
                                    pagerState.animateScrollToPage(0)
                                }
                            })
                    Spacer(Modifier.size(15.dp))
                    Text(text = "연관채널",
                        fontSize = 18.sp,
                        color =
                        if (pagerState.currentPage == 1) MDPoint
                        else MDGray,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.bounceClick {
                            scope.launch {
                                pagerState.animateScrollToPage(1)
                            }

                        }
                    )
                    Spacer(Modifier.size(15.dp))
                    Text(text = "연관기업",
                        fontSize = 18.sp,
                        color =
                        if (pagerState.currentPage == 2) MDPoint
                        else MDGray,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.bounceClick {
                            scope.launch {
                                pagerState.animateScrollToPage(2)
                            }

                        }
                    )
                    Spacer(Modifier.size(15.dp))
                    Text(text = "연관뉴스",
                        fontSize = 18.sp,
                        color =
                        if (pagerState.currentPage == 3) MDPoint
                        else MDGray,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.bounceClick {
                            scope.launch {
                                pagerState.animateScrollToPage(3)
                            }

                        }
                    )
                }
                //포스트, 큐레이션 텝 레이아웃
                HorizontalPager(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp)
                        .padding(horizontal = 18.dp),
                    verticalAlignment = Alignment.Top,
                    count = 4,
                    state = pagerState
                ) { page ->
                    when (page) {
                        //나중에 API로 받은 값(List)도 넣어줘야할듯
                        0 -> NormalInformationScreen(cardData)
                            1 -> RelatedInformationScreen(navHostController, cardData)
                            2 -> RelatedCompanyScreen(navHostController, cardData)
                            3 -> RelatedNewsScreen(navHostController, cardData)
                    }

                }

                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .bounceClick {
                            focusManager.clearFocus()
                            showModalSheet.value = !showModalSheet.value
                            scope.launch {
                                bottomSheetState.show()
                            }
                        }
                        .height(80.dp)
                        .fillMaxWidth()
//                        .clip(RoundedCornerShape(10.dp))
                        .background(color = MDPoint),
                    contentAlignment = Alignment.Center

                ) {
                    Text(
                        text = "폴더 지정하기",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight(700),
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center),
                        textAlign = TextAlign.Center
                    )
                }
//                Box(
//                    modifier = Modifier
//                        .wrapContentHeight()
//                        .fillMaxWidth()
//                        .bounceClick {
//                        }
//                        .clip(RoundedCornerShape(30.dp))
//                        .background(color = MDPoint)
//                ) {
//                    Text(
//                        text = "다음",
//                        color = Color.White,
//                        fontSize = 15.sp,
//                        fontWeight = FontWeight(700),
//                        modifier = Modifier
//                            .padding(vertical = 14.dp),
//                        textAlign = TextAlign.Center
//                    )
//                }
//                Spacer(modifier = Modifier.height(27.dp))
            }

        }
    )
}