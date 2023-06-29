package com.example.sw_univ_hackathon.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sw_univ_hackathon.R
import com.example.sw_univ_hackathon.ui.theme.MDPoint
import com.example.sw_univ_hackathon.util.EEditText
import com.plzgpt.lenzhub.util.addFocusCleaner
import com.plzgpt.lenzhub.util.bounceClick

@Composable
fun CardAddScreen(navController: NavHostController) {
    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()

    val isIdTextFieldSearchFocused = remember { mutableStateOf(false) } //textField가 포커싱 되어 있는지 여부.
    val isEmailTextFieldSearchFocused = remember { mutableStateOf(false) } //textField가 포커싱 되어 있는지 여부.
    val isPhoneTextFieldSearchFocused = remember { mutableStateOf(false) } //textField가 포커싱 되어 있는지 여부.
    val isCompanyTextFieldSearchFocused = remember { mutableStateOf(false) } //textField가 포커싱 되어 있는지 여부.
    val idText = remember { mutableStateOf("최아리") } //textField 데이터 값.
    val emailText = remember { mutableStateOf("2222@naver.com") } //textField 데이터 값.
    val phoneNumberText = remember { mutableStateOf("010-6669-1893") } //textField 데이터 값.
    val companyText = remember { mutableStateOf("한림대학교") } //textField 데이터 값.

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .addFocusCleaner(focusManager)
    ) {

        //상단 카테고리 선택 및 검색버튼 있는 레이아웃
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 10.dp, bottom = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_chevron_left),
                contentDescription = null,
                modifier = Modifier
                    .bounceClick {

                    }
            )

            Text(
                text = "명함 추가하기",
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

        Text(
            text = "해당 정보가 맞나요?",
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight(500),
            modifier = Modifier
                .padding(bottom = 36.dp)
        )

        Row(
            modifier = Modifier
                .padding(bottom = 36.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(90.dp)
            ) {
                Text(
                    text = "이름",
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontWeight = FontWeight(500)
                )
            }
            EEditText(
                searchText = idText,
                isTextFieldSearchFocused = isIdTextFieldSearchFocused,
                focusManager = focusManager
            )
//            TextField(
//                value = idText.value,
//                onValueChange = { newText -> idText.value = newText },
////                color = Color.Black,
////                fontSize = 15.sp,
//                singleLine = true,
//                modifier = Modifier
//                    .padding(end = 5.dp),
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//            )
        }

        Row(
            modifier = Modifier
                .padding(bottom = 36.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(90.dp)
            ) {
                Text(
                    text = "이메일",
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontWeight = FontWeight(500)
                )
            }

            EEditText(
                searchText = emailText,
                isTextFieldSearchFocused = isEmailTextFieldSearchFocused,
                focusManager = focusManager
            )
        }

        Row(
            modifier = Modifier
                .padding(bottom = 36.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(90.dp)
            ) {
                Text(
                    text = "전화번호",
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontWeight = FontWeight(500)
                )
            }

            EEditText(
                searchText = phoneNumberText,
                isTextFieldSearchFocused = isPhoneTextFieldSearchFocused,
                focusManager = focusManager
            )
        }

        Row(
            modifier = Modifier
                .padding(bottom = 36.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(90.dp)
            ) {
                Text(
                    text = "직장명",
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontWeight = FontWeight(500)
                )
            }

            EEditText(
                searchText = companyText,
                isTextFieldSearchFocused = isCompanyTextFieldSearchFocused,
                focusManager = focusManager
            )
        }


        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .bounceClick {

                }
                .clip(RoundedCornerShape(30.dp))
                .background(color = MDPoint)
        ) {
            Text(
                text = "다음",
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight(700),
                modifier = Modifier
                    .padding(vertical = 14.dp)
                    .align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(27.dp))

    }
}