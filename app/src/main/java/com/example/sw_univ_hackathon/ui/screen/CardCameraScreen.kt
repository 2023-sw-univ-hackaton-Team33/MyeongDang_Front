package com.example.sw_univ_hackathon.ui.screen

import CardTakePictureScreen
import android.content.Context
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.ujizin.camposer.CameraPreview
import com.ujizin.camposer.state.rememberCameraState
import java.io.File


// 새 파일을 만들기 위한 함수
private fun Context.createNewFile() = File(
    filesDir, "${System.currentTimeMillis()}.jpg"
).apply { createNewFile() }

@Composable
fun CardCameraScreen(modifier: Modifier, navHostController: NavHostController) {
//    val cameraState = rememberCameraState()
//    val context = LocalContext.current
//    CameraPreview(
//        cameraState = cameraState,
//    ) {
//        Button(onClick = {
//            cameraState.takePicture(context.createNewFile()) { result ->
//                // Result는 사진이 성공적으로 저장되었는지 여부를 알려줌
//            }
//        }) {  Text("Take Picture") }
//    }
    CardTakePictureScreen(navHostController)
}