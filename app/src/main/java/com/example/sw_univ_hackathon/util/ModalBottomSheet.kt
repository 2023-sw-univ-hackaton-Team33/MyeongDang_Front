package com.plzgpt.lenzhub.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sw_univ_hackathon.R
import com.example.sw_univ_hackathon.ui.theme.MDPoint
import kotlinx.coroutines.launch


//바텀 모달 시트
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ModalBottomSheet(
    title: String,
    bottomSheetState: ModalBottomSheetState,
    uiScreen: @Composable () -> Unit,
    sheetScreen: @Composable () -> Unit,
) {
    ModalBottomSheetLayout(
        sheetElevation = 0.dp,
        sheetBackgroundColor = Color.Transparent,
        sheetState = bottomSheetState,
        sheetContent = {
            Box(
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                Column() {
                    Box(
                        modifier = Modifier
                            .offset(y = 35.dp)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_folder),
                            contentDescription = null,
                            modifier = Modifier
                                .size(70.dp)
                        )
                    }

                    Card(
                        shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
                                color = Color.Transparent
                            )
                    ) {

                        Box() {
                            Image(
                                painter = painterResource(id = R.drawable.ic_trash),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(top = 20.dp, start = 20.dp)
                                    .bounceClick {

                                    }
                            )

//                            Spacer(modifier = Modifier.weight(1f))

                            Box(
                                modifier = Modifier
                                    .offset(y = (-35).dp)
                                    .fillMaxWidth()
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_folder),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(70.dp)
                                        .align(Alignment.Center)
                                )
                            }


                            Image(
                                painter = painterResource(id = R.drawable.ic_gray_plus),
                                contentDescription = null,
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .padding(top = 20.dp, end = 20.dp)
                                    .bounceClick {

                                    }
                            )
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 18.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            // 회색 홀드
//                    Box(modifier = Modifier
//                        .width(40.dp)
//                        .height(5.dp)
//                        .background(LHGray, RoundedCornerShape(30.dp))
//                        .alpha(0.2f)
//                    )
//                    Image()
                            Spacer(modifier = Modifier.size(30.dp))
                            if (title != "") {
                                Text(
                                    text = title,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                    modifier = Modifier
                                        .align(Alignment.Start)
                                        .padding(start = 18.dp)
                                )
                                Spacer(modifier = Modifier.height(30.dp))
                            }


                            // 리스트
                            LazyColumn(
                                modifier = Modifier
                                    .padding(horizontal = 18.dp)
                            ) {
                                item {
                                    sheetScreen.invoke()
                                }
                            }
                        }
                    }
                }
            }
        }) {
        uiScreen.invoke()
    }
}

//Modal Bottom Sheet item
@Composable
fun ModalBottomSheetItem(
    text: String,
    trailing: Boolean = true,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 10.dp)
        ) {
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text,
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.weight(1f))
            if (trailing) {
                Image(
                    painter = painterResource(id = R.drawable.ic_chevron_right),
                    contentDescription = null,
                    modifier = Modifier
                        .width(15.dp)
                        .height(15.dp)
                        .align(Alignment.CenterVertically)
                )
            }
        }
    }
}