import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import coil.compose.rememberImagePainter
import com.example.sw_univ_hackathon.R
import com.example.sw_univ_hackathon.api.ImageResult
import com.example.sw_univ_hackathon.api.RetrofitBuilder
import com.example.sw_univ_hackathon.ui.route.NAV_ROUTE
import com.example.sw_univ_hackathon.ui.screen.ClovaAPICall
import com.example.sw_univ_hackathon.ui.theme.MDPoint
import com.example.sw_univ_hackathon.util.UriUtil.toFile
import com.example.sw_univ_hackathon.util.bounceClick
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okio.BufferedSink
import okio.source
import retrofit2.Callback
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun CardTakePictureScreen(navHostController: NavHostController) {

    val context = LocalContext.current
    val contextFile = context.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        "com.example.sw_univ_hackathon" + ".provider", contextFile
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
                            Log.d("image-result filename", contextFile.name)

//                            val filePart = capturedImageUri.asMultipart("file", context.contentResolver)

                            val file2 = toFile(context, capturedImageUri)

                            val file = MultipartBody.Part.createFormData(
                                name = "file",
                                filename = file2.name,
                                body = file2.asRequestBody("image/*".toMediaType())
                            )

//                            val fileBody: RequestBody = file2
//                                .asRequestBody("image/*".toMediaTypeOrNull())
//
//                            val filePart = MultipartBody.Part.createFormData(
//                                "file",
//                                file2.name,
//                                fileBody
//                            )

                            RetrofitBuilder.imageAPI
                                .imageUpload(file)
                                .enqueue(object : Callback<ImageResult> {
                                    override fun onResponse(
                                        call: retrofit2.Call<ImageResult>,
                                        response: retrofit2.Response<ImageResult>,
                                    ) {
                                        if (response.isSuccessful) {
                                            val res = response.body()
                                            if (res != null) {

                                                Log.d("image-result filename111", contextFile.name)
                                                ClovaAPICall(
                                                    res.url,
                                                    contextFile,
                                                    context,
                                                    capturedImageUri
                                                )

                                                Log.e("apires", res.toString())
                                            } else {
                                                Log.e("apires", response.toString())
                                            }
                                        } else {
                                        }
                                    }

                                    override fun onFailure(
                                        call: retrofit2.Call<ImageResult>,
                                        t: Throwable
                                    ) {
                                        Log.d("image-result filename222", contextFile.name)

                                    }


                                })

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

@SuppressLint("Range")
fun Uri.asMultipart(name: String, contentResolver: ContentResolver): MultipartBody.Part? {
    return contentResolver.query(this, null, null, null, null)?.let {
        if (it.moveToNext()) {
            val displayName = it.getString(it.getColumnIndex(OpenableColumns.DISPLAY_NAME));
            val requestBody = object : RequestBody() {
                override fun contentType(): MediaType? {
                    return contentResolver.getType(this@asMultipart)?.toMediaType()
                }

                override fun writeTo(sink: BufferedSink) {
                    sink.writeAll(contentResolver.openInputStream(this@asMultipart)?.source()!!)
                }
            }
            it.close()
            MultipartBody.Part.createFormData(name, displayName, requestBody)
        } else {
            it.close()
            null
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

