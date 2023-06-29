package com.example.sw_univ_hackathon.util

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sw_univ_hackathon.R
import com.example.sw_univ_hackathon.db.BusinessCardDatabase
import com.example.sw_univ_hackathon.ui.theme.MDBlack
import com.example.sw_univ_hackathon.ui.theme.MDGray
import com.example.sw_univ_hackathon.ui.theme.MDPoint
import com.example.sw_univ_hackathon.ui.theme.MDPointAlpha
import com.plzgpt.lenzhub.util.bounceClick


//탐색에 검색창
@Composable
fun EEditText(
    searchText : MutableState<String>,
    isTextFieldSearchFocused : MutableState<Boolean>,
    focusManager : FocusManager,
) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ){
            //검색어 입력하는 텍스트 필드
            TextField(
                value = searchText.value,
                onValueChange = { searchText.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .onFocusChanged {
                        isTextFieldSearchFocused.value = it.isFocused
                    },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor =
                    if (isTextFieldSearchFocused.value) MDPointAlpha
                    else Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                textStyle = TextStyle(fontSize = 14.sp, color = MDBlack),
                keyboardActions = KeyboardActions(onSearch = {
                    focusManager.clearFocus()
                    isTextFieldSearchFocused.value = false

                    Log.d("keyboard-test", searchText.value)
                    if(searchText.value != "") {
                        //이미 전에 검색했던 거면 한번 지우고 다시 insert해줘서 맨 위로 올려줌
//                    val checkData: BusinessCard? = db.businessCardDao().findRecentSearchBySearchText(searchText.value)
//                    checkData?.let {
//                        db.businessCardDao().delete(
//                            it
//                        )
//                    }
//
//                    navController.navigate(route = NAV_ROUTE_SEARCH.DISCOVERSEARCHRESULT.routeName + "/" + searchText.value) {
//                        popUpTo(NAV_ROUTE_SEARCH.DISCOVERSEARCHING.routeName)
//                    }


                    }
                }),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search,
                    keyboardType = KeyboardType.Text
                ),
                singleLine = true,
                placeholder = {
                    Text(
                        text = "원하시는 정보를 검색해보세요",
                        style = TextStyle(color = MDGray)
                    )
                }
            )
            if (searchText.value.isNotEmpty()) {
                Image(
                    painterResource(id = R.drawable.ic_x_circle),
                    contentDescription = "검색중 나오는 x 이미지",
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 10.dp, top = 5.dp)
                        .bounceClick {
                            searchText.value = ""
                        }
                )
            }

        }
}
