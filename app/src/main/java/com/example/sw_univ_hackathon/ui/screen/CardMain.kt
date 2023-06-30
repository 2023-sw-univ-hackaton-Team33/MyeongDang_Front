package com.example.sw_univ_hackathon.ui.screen

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.NavHostController
import androidx.room.Room
import coil.compose.rememberImagePainter
import com.example.sw_univ_hackathon.R
import com.example.sw_univ_hackathon.db.BusinessCardDatabase
import com.example.sw_univ_hackathon.ui.data.BusinessCard
import com.example.sw_univ_hackathon.ui.route.NAV_ROUTE
import com.example.sw_univ_hackathon.ui.theme.MDBlack
import com.example.sw_univ_hackathon.ui.theme.MDPoint
import com.example.sw_univ_hackathon.ui.theme.MDPointAlpha
import com.example.sw_univ_hackathon.util.ModalBottomSheet
import com.example.sw_univ_hackathon.util.ModalBottomSheetItem
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.example.sw_univ_hackathon.util.SearchTextField
import com.example.sw_univ_hackathon.util.addFocusCleaner
import com.example.sw_univ_hackathon.util.bounceClick
import com.gun0912.tedpermission.provider.TedPermissionProvider.context
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects

@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class, ExperimentalPagerApi::class)
@Composable
fun CardMainScreen(navController: NavHostController) {
    val pagerState = rememberPagerState()

    val focusManager = LocalFocusManager.current

    val showModalSheet = rememberSaveable { mutableStateOf(false) }
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    val scope = rememberCoroutineScope()

    val searchText = remember { mutableStateOf("") } //textField 데이터 값.
    val isTextFieldSearchFocused = remember { mutableStateOf(false) } //textField가 포커싱 되어 있는지 여부.

    val applicationContext = LocalContext.current.applicationContext


    val db = Room.databaseBuilder(
        applicationContext, BusinessCardDatabase::class.java, "md_card_database"
    ).allowMainThreadQueries().build()


    // opensource
    // url : https://github.com/dheeraj-bhadoria/android-camera-example-and-compose-capture-image-jetpack-compose
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
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .addFocusCleaner(focusManager)
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 20.dp)
            ) {

                Column(
                    modifier = Modifier.fillMaxSize()
                ) {

                    //상단 레이아웃
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(top = 20.dp, bottom = 18.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_logo),
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp)
                                .padding(end = 7.dp),
                            contentDescription = null,
                        )
                        Text(
                            text = "명당",
                            color = MDBlack,
                            fontSize = 24.sp,
                            fontWeight = FontWeight(500)
                        )

                        Spacer(modifier = Modifier.weight(1f))


                    }


                    SearchTextField(
                        searchText = searchText,
                        isTextFieldSearchFocused = isTextFieldSearchFocused,
                        focusManager = focusManager,
                        db = db,
                        navController = navController
                    )

                    Spacer(modifier = Modifier.height(24.dp))

//                    Image(
//                        painter = painterResource(id = R.drawable.ic_logo),
//                        modifier = Modifier
//                            .padding(horizontal = 10.dp)
//                            .width(300.dp)
//                            .height(166.dp)
//                            .clip(shape=RoundedCornerShape(5.dp)),
//                        contentDescription = null,
//                    )
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(190.dp)
                            .padding(20.dp, 10.dp)
                            .clip(shape=RoundedCornerShape(5.dp))
                            .bounceClick {
                                navController.navigate(route = NAV_ROUTE.CARDCAMERA.routeName)
                            },
                        contentScale = ContentScale.Crop,
                        painter = rememberImagePainter(R.drawable.ic_plus_card),
                        contentDescription = null
                    )
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(190.dp)
                            .padding(20.dp, 10.dp)
                            .clip(shape=RoundedCornerShape(5.dp))
                            .shadow(10.dp, shape = RoundedCornerShape(10.dp))
                            .bounceClick {
                                navController.navigate(NAV_ROUTE.CARDDETAIL.routeName+"/b")

                            },
                        contentScale = ContentScale.Crop,
                        painter = rememberImagePainter(BusinessCard.CARD3.image),
                        contentDescription = null
                    )
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(190.dp)
                            .padding(20.dp, 10.dp)
                            .clip(shape=RoundedCornerShape(5.dp))
                            .shadow(20.dp, shape = RoundedCornerShape(6.dp))
                            .bounceClick {
                                navController.navigate(NAV_ROUTE.CARDDETAIL.routeName+"/a")
                            },
                        contentScale = ContentScale.Crop,
                        painter = rememberImagePainter(BusinessCard.CARD2.image),
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.weight(1f))

//                    LazyColumn(
//                        modifier = Modifier.fillMaxSize(),
//                        contentPadding = PaddingValues(horizontal = 18.dp, vertical = 18.dp)
//                    ) { items(
//                        items = posts,
//                        key = {post -> post.id }
//                    ) { item ->
//                        MainBusinessCard(item)
//                    }
//                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(bottom = 0.dp)
                            .padding(horizontal = 70.dp)
                    ) {
                        Column(
                            modifier = Modifier,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(60.dp)
                                    .clip(shape = RoundedCornerShape(10.dp))
                                    .background(color = MDPointAlpha)
                                    .bounceClick {
//                                    추가 버튼 눌렀을 때
                                        navController.navigate(route = NAV_ROUTE.CARDCAMERA.routeName)
//                                        navController.navigate(route = NAV_ROUTE.CARDDETAIL.routeName)
                                    },
                                contentAlignment = Center

                            ) {

                                Image(
                                    painter = painterResource(id = R.drawable.ic_camera),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(24.dp)

                                )

                            }
                            Text(
                                text = "카메라",
                                color = MDPoint,
                                fontSize = 12.sp,
                                fontWeight = FontWeight(500),
                                modifier = Modifier
                                    .padding(5.dp)
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))

                        Column(
                            modifier = Modifier,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(60.dp)
                                    .clip(shape = RoundedCornerShape(10.dp))
                                    .background(color = MDPointAlpha)
                                    .bounceClick {
//                                    navController.navigate(route = NAV_ROUTE.DISCOVERSEARCHING.routeName)
                                        focusManager.clearFocus()
                                        showModalSheet.value = !showModalSheet.value
                                        scope.launch {
                                            bottomSheetState.show()

                                        }
                                    },
                                contentAlignment = Center

                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_folder),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(24.dp)


                                )

                            }
                            Text(
                                text = "폴더",
                                color = MDPoint,
                                fontSize = 12.sp,
                                fontWeight = FontWeight(500),
                                modifier = Modifier
                                    .padding(5.dp)
                            )
                        }
                    }

                }
            }


        }
    )
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

