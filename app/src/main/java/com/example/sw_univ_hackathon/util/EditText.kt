package com.plzgpt.lenzhub.util

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sw_univ_hackathon.ui.theme.MDBlack
import com.example.sw_univ_hackathon.ui.theme.MDGray
import com.example.sw_univ_hackathon.ui.theme.MDPoint
import com.example.sw_univ_hackathon.ui.theme.MDPointAlpha

//커스텀 TextField made by taeyoon
@Composable
fun EditText(
    title: String,
    data: MutableState<String>,
    isTextFieldFocused: MutableState<Boolean>,
    modifier: Modifier = Modifier
        .fillMaxWidth(),
    maxLines: Int = 1,
    keyboardType: KeyboardType = KeyboardType.Text,
    singleLine: Boolean = false,
) {
    val focusRequester = remember { FocusRequester() }
    Column {
        Text(title, fontWeight = FontWeight.Bold, fontSize = 12.sp, color = if (isTextFieldFocused.value) MDPoint else MDGray)
        Spacer(modifier = Modifier.height(5.dp))
        TextField(
            modifier = modifier
                .focusRequester(focusRequester)
                .onFocusChanged {
                    isTextFieldFocused.value = it.isFocused
                },
            value = data.value,
            onValueChange = { textValue ->
                data.value = textValue
            },
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = if (isTextFieldFocused.value) MDPointAlpha else Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            textStyle = TextStyle(fontSize = 20.sp, color = MDBlack),
            maxLines = maxLines,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            singleLine = singleLine
        )
    }
}