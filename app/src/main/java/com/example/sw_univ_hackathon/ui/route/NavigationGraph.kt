package com.example.sw_univ_hackathon.ui.route

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sw_univ_hackathon.ui.screen.CardAddScreen
import com.example.sw_univ_hackathon.ui.screen.CardCameraScreen
import com.example.sw_univ_hackathon.ui.screen.CardMainScreen


enum class NAV_ROUTE(val routeName: String, val description: String) { //upload 패키지 루트.
    CARDMAIN("CARD_MAIN", "명함 메인 창"),
    CARDFOLDER("CARD_FOLDER", "명함 폴더 창"),
    CARDCAMERA("CARD_CAMERA", "명함 카메라 창"),
    CARDADD("CARD_ADD", "명함 제작 정보 창"),
    DISCOVERSEARCHUSERPROFILE("DISCOVER_SEARCH_USER_PROFILE", "탐색 유저 프로필")
}


@Composable
fun NavigationGraph(
    navController: NavHostController
) {

    NavHost(navController, startDestination = NAV_ROUTE.CARDMAIN.routeName,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(
            NAV_ROUTE.CARDMAIN.routeName
        ) {
            CardCameraScreen(modifier = Modifier ,navController)
            CardMainScreen(navController)
        }
        composable(
            NAV_ROUTE.CARDFOLDER.routeName,
        ) { backStackEntry ->
//            SearchingScreen(navController)
        }
        composable(
            NAV_ROUTE.CARDCAMERA.routeName + "/{searchText}",
        ) { backStackEntry ->
            val searchText = backStackEntry.arguments?.getString("searchText") ?: ""
//            SearchResultScreen(navController, searchText)
        }
        composable(
            NAV_ROUTE.CARDADD.routeName
        ) {
            CardCameraScreen(modifier = Modifier ,navController)
//            CardAddScreen(navController)
        }
    }
}