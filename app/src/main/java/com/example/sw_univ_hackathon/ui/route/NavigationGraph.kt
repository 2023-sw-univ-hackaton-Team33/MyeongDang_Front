package com.example.sw_univ_hackathon.ui.route

import CardTakePictureScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.sw_univ_hackathon.ui.screen.CardAddScreen
import com.example.sw_univ_hackathon.ui.screen.CardDetailScreen
import com.example.sw_univ_hackathon.ui.screen.CardMainScreen
import com.example.sw_univ_hackathon.ui.screen.WebViewScreen


enum class NAV_ROUTE(val routeName: String, val description: String) { //upload 패키지 루트.
    CARDMAIN("CARD_MAIN", "명함 메인 창"),
    CARDFOLDER("CARD_FOLDER", "명함 폴더 창"),
    CARDCAMERA("CARD_CAMERA", "명함 카메라 창"),
    CARDADD("CARD_ADD", "명함 제작 정보 창"),
    CARDDETAIL("CARD_DETAIL", "명함 상세 정보 창"),
    WEBVIEW("WEB_VIEW", "웹뷰 창")

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
            CardMainScreen(navController)
        }
        composable(
            NAV_ROUTE.CARDFOLDER.routeName,
        ) { backStackEntry ->
//            SearchingScreen(navController)
        }
        composable(
            NAV_ROUTE.CARDCAMERA.routeName,
        ) { backStackEntry ->
            CardTakePictureScreen(navController)
        }
        composable(
            NAV_ROUTE.CARDADD.routeName
        ) {
//            CardCameraScreen(modifier = Modifier ,navController)
            CardAddScreen(navController)
        }
        composable(
            NAV_ROUTE.CARDDETAIL.routeName+"/{key}"
        ) {backStackEntry ->
            val key = backStackEntry.arguments?.getString("key") ?: "c"
            CardDetailScreen(navController, cardKey = key)
        }
        composable(
            NAV_ROUTE.WEBVIEW.routeName+"/{url}"
        ) { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url") ?: ""
            WebViewScreen(navController, url = url)
        }
//        composable(
//            NAV_ROUTE.WEBVIEW.routeName + "/{url}",
//        ) {backStackEntry ->
////            CardCameraScreen(modifier =
//            val url = backStackEntry.arguments?.getString("url") ?: ""
//            WebViewScreen(url)
//        }
    }
}