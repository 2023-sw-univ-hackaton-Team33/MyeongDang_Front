package com.example.sw_univ_hackathon.ui.screen

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.room.Room
import com.example.sw_univ_hackathon.R
import com.example.sw_univ_hackathon.db.BusinessCardDatabase
import com.example.sw_univ_hackathon.ui.route.NAV_ROUTE
import com.example.sw_univ_hackathon.ui.theme.MDBlack
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.plzgpt.lenzhub.util.ModalBottomSheet
import com.example.sw_univ_hackathon.util.SearchTextField
import com.plzgpt.lenzhub.util.addFocusCleaner
import com.plzgpt.lenzhub.util.bounceClick
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class, ExperimentalPagerApi::class)
@Composable
fun CardMainScreen(navController: NavHostController) {
    val pagerState = rememberPagerState()

    val focusManager = LocalFocusManager.current

    val showModalSheet = rememberSaveable{ mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    val scope = rememberCoroutineScope()

    val searchText = remember { mutableStateOf("") } //textField 데이터 값.
    val isTextFieldSearchFocused = remember { mutableStateOf(false) } //textField가 포커싱 되어 있는지 여부.

    val applicationContext = LocalContext.current.applicationContext


    val db = Room.databaseBuilder(
        applicationContext, BusinessCardDatabase::class.java, "md_card_database"
    ).allowMainThreadQueries().build()

    ModalBottomSheet(
        title = "",
        bottomSheetState = bottomSheetState,
        sheetScreen = {

//            ModalBottomSheetItem(text = "동물", icon = R.drawable.ic_animal, trailing = true, modifier = Modifier.bounceClick {
//                selectedCategory.value = Category.ANIMAL
//
//                scope.launch {
//                    bottomSheetState.hide()
//                }
//            })
//            ModalBottomSheetItem(text = "인물", icon = R.drawable.ic_person, trailing = true, modifier = Modifier.bounceClick {
//
//                selectedCategory.value = Category.PERSON
//
//                scope.launch {
//                    bottomSheetState.hide()
//                }
//            })
//            ModalBottomSheetItem(text = "풍경", icon = R.drawable.ic_sight, trailing = true, modifier = Modifier.bounceClick {
//                selectedCategory.value = Category.SIGHT
//
//                scope.launch {
//                    bottomSheetState.hide()
//                }
//            })
//            ModalBottomSheetItem(text = "음식", icon = R.drawable.ic_food, trailing = true, modifier = Modifier.bounceClick {
//                selectedCategory.value = Category.FOOD
//
//                scope.launch {
//                    bottomSheetState.hide()
//                }
//            })
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

                        Image(
                            painter = painterResource(id = R.drawable.ic_plus),
                            contentDescription = null,
                            modifier = Modifier
                                .size(36.dp)
                                .bounceClick {
//                                    추가 버튼 눌렀을 때
                                    navController.navigate(route = NAV_ROUTE.CARDADD.routeName)
                                }

                        )
                    }

                    SearchTextField(
                        searchText =  searchText,
                        isTextFieldSearchFocused = isTextFieldSearchFocused,
                        focusManager = focusManager,
                        db = db,
                        navController = navController
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

                    Image(
                        painter = painterResource(id = R.drawable.ic_folder),
                        contentDescription = null,
                        modifier = Modifier
                            .size(70.dp)
                            .bounceClick {
//                                    추가 버튼 눌렀을 때
//                                    navController.navigate(route = NAV_ROUTE.DISCOVERSEARCHING.routeName)
                                focusManager.clearFocus()
                                showModalSheet.value = !showModalSheet.value
                                scope.launch {
                                    bottomSheetState.show()

                                }
                            }
                            .align(alignment = CenterHorizontally)

                    )
                }
            }

        }
    )
}