import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.sw_univ_hackathon.R
import com.example.sw_univ_hackathon.ui.route.NAV_ROUTE
import com.example.sw_univ_hackathon.ui.theme.MDPoint
import com.plzgpt.lenzhub.util.bounceClick
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun CardTakePictureScreen(navHostController: NavHostController) {

    val context = LocalContext.current
    val file = context.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        "com.example.sw_univ_hackathon" + ".provider", file
    )

    var capturedImageUri by remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }

    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
            capturedImageUri = uri
        }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            cameraLauncher.launch(uri)
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }
//
//    Column(
//        Modifier
//            .fillMaxSize()
//            .padding(10.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Button(onClick = {
//            val permissionCheckResult =
//                ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
//            if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
//                cameraLauncher.launch(uri)
//            } else {
//                // Request a permission
//                permissionLauncher.launch(Manifest.permission.CAMERA)
//            }
//        }) {
//            Text(text = "Capture Image From Camera")
//        }
//    }

    if (capturedImageUri.path?.isNotEmpty() == true) {
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
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .padding(20.dp, 10.dp),
                contentScale = ContentScale.Crop,
                painter = rememberImagePainter(capturedImageUri),
                contentDescription = null
            )

            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .bounceClick {
                        if (capturedImageUri.path?.isNotEmpty() == true) {
                            navHostController.navigate(NAV_ROUTE.CARDADD.routeName)
                        }
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

    } else {
        val permissionCheckResult =
            ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)

        if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {

            SideEffect {
                cameraLauncher.launch(uri)
            }
        } else {
            // Request a permission
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }

    }


}

fun Context.createImageFile(): File {
    // Create an image file name
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    val image = File.createTempFile(
        imageFileName, /* prefix */
        ".jpg", /* suffix */
        externalCacheDir      /* directory */
    )
    return image
}

