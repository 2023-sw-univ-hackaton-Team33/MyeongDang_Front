package com.example.sw_univ_hackathon.util

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
import com.example.sw_univ_hackathon.ui.theme.MDGray
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
            ) {
                Column() {

                    Card(
                        shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
                                color = Color.Transparent
                            )
                    ) {

                        Column(
                            modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            // 회색 홀드
                            Box(modifier = Modifier
                                .width(70.dp)
                                .height(3.dp)
                                .background(MDGray, RoundedCornerShape(30.dp))
                                .alpha(0.2f)
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {

                                Spacer(modifier = Modifier.weight(1f))
                                Image(
                                    painter = painterResource(id = R.drawable.ic_gray_plus),
                                    contentDescription = null,
                                    modifier = Modifier
                                    .padding(end = 20.dp)
                                        .bounceClick {

                                        },
                                    alignment = Alignment.TopEnd
                                )
                            }
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
                .padding(vertical = 20.dp)
        ) {
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