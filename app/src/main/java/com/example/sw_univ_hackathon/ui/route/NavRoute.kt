//package com.example.sw_univ_hackathon.ui.route
//
//import android.os.Build
//import androidx.annotation.RequiresApi
//import androidx.compose.animation.AnimatedContentScope
//import androidx.compose.animation.ExperimentalAnimationApi
//import androidx.compose.animation.core.tween
//import androidx.compose.animation.fadeIn
//import androidx.compose.animation.fadeOut
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.lazy.LazyListState
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.navigation.NavHostController
//import com.google.accompanist.navigation.animation.composable
//import com.google.accompanist.navigation.animation.AnimatedNavHost
//import com.plzgpt.lenzhub.ApplicationClass
//import com.plzgpt.lenzhub.R
//import com.plzgpt.lenzhub.ui.screen.main.MainScreen
//import com.plzgpt.lenzhub.ui.screen.profile.ProfileScreen
//import com.plzgpt.lenzhub.ui.screen.search.SearchScreen
//import com.plzgpt.lenzhub.ui.screen.lenz.UploadScreen
//import kotlinx.coroutines.CoroutineScope
//
//enum class NAV_ROUTE_BNB(val routeName: String, val description: String, val icon: Int) { //main 패키지 루트.
//    FOLLOW("Main", "피드", R.drawable.ic_home),
//    DISCOVER("DISCOVER", "탐색", R.drawable.ic_search_small),
//    UPLOAD("UPLOAD", "필터", R.drawable.ic_filter),
//    MYPROFILE("MYPROFILE", "내 프로필", R.drawable.ic_user),
//
//}
//@RequiresApi(Build.VERSION_CODES.O)
//@OptIn(ExperimentalAnimationApi::class)
//@Composable
//fun NavigationGraphBNB(
//    navController: NavHostController,
//    navFollowController:NavHostController,
//    scope: CoroutineScope,
//    lazyScroll:LazyListState
//) {
//
//    val fadeInDuration = 500
//    val fadeOutDuration = 50
//    val slideInDuration = 200
//    val slideOutDuration = 200
//    val userId = ApplicationClass.sharedPreferences.getInt(ApplicationClass.clientId, 0)
//
//    AnimatedNavHost(navController, startDestination = NAV_ROUTE_BNB.FOLLOW.routeName,
//        modifier = Modifier.fillMaxSize()) {
////        composable(NAV_ROUTE_FOLLOW.USERPROFILE.routeName){
////            ProfileScreen()
////        }
//        composable(
//            NAV_ROUTE_BNB.FOLLOW.routeName,
//            enterTransition = {
//                fadeIn(animationSpec = tween(fadeInDuration)) +
//                        slideIntoContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(slideInDuration))
//            },
//            exitTransition = {
//                fadeOut(animationSpec = tween(fadeOutDuration)) +
//                        slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(slideOutDuration))
//            }
//        ) {
//            MainScreen()
//        }
//
//        composable(
//            NAV_ROUTE_BNB.DISCOVER.routeName,
//            enterTransition = {
//                when(initialState.destination.route) {
//                    NAV_ROUTE_BNB.FOLLOW.routeName ->
//                        fadeIn(animationSpec = tween(fadeInDuration)) +
//                                slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(slideInDuration))
//                    else ->
//                        fadeIn(animationSpec = tween(fadeInDuration)) +
//                                slideIntoContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(slideInDuration))
//                }
//            }
//        ) { SearchScreen() }
//        composable( //업로드
//            NAV_ROUTE_BNB.UPLOAD.routeName,
//            enterTransition = {
//                when (initialState.destination.route) {
//                    NAV_ROUTE_BNB.FOLLOW.routeName ->
//                        fadeIn(animationSpec = tween(fadeInDuration)) +
//                                slideIntoContainer(
//                                    AnimatedContentScope.SlideDirection.Left,
//                                    animationSpec = tween(slideInDuration)
//                                )
//                    NAV_ROUTE_BNB.DISCOVER.routeName ->
//                        fadeIn(animationSpec = tween(fadeInDuration)) +
//                                slideIntoContainer(
//                                    AnimatedContentScope.SlideDirection.Left,
//                                    animationSpec = tween(slideInDuration)
//                                )
//                    else ->
//                        fadeIn(animationSpec = tween(fadeInDuration)) +
//                                slideIntoContainer(
//                                    AnimatedContentScope.SlideDirection.Right,
//                                    animationSpec = tween(slideInDuration)
//                                )
//                }
//            },
//            exitTransition = {
//                when(targetState.destination.route) {
//                    NAV_ROUTE_BNB.FOLLOW.routeName ->
//                        fadeOut(animationSpec = tween(fadeOutDuration)) +
//                                slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(slideOutDuration))
//                    NAV_ROUTE_BNB.DISCOVER.routeName ->
//                        fadeOut(animationSpec = tween(fadeOutDuration)) +
//                                slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(slideOutDuration))
//                    else ->
//                        fadeOut(animationSpec = tween(fadeOutDuration)) +
//                                slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(slideOutDuration))
//                }
//            }
//        ) { UploadScreen() }
////        composable(
////            NAV_ROUTE_BNB.NOTIFICATION.routeName,
////            enterTransition = {
////                when(initialState.destination.route) {
////                    NAV_ROUTE_BNB.FOLLOW.routeName ->
////                        fadeIn(animationSpec = tween(fadeInDuration)) +
////                                slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(slideInDuration))
////                    NAV_ROUTE_BNB.DISCOVER.routeName ->
////                        fadeIn(animationSpec = tween(fadeInDuration)) +
////                                slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(slideInDuration))
////                    NAV_ROUTE_BNB.UPLOAD.routeName ->
////                        fadeIn(animationSpec = tween(fadeInDuration)) +
////                                slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(slideInDuration))
////                    NAV_ROUTE_BNB.MYPROFILE.routeName ->
////                        fadeIn(animationSpec = tween(fadeInDuration)) +
////                                slideIntoContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(slideInDuration))
////                    else -> fadeIn(animationSpec = tween(fadeInDuration))
////                }
////            },
////            exitTransition = {
////                when(targetState.destination.route) {
////                    NAV_ROUTE_BNB.FOLLOW.routeName ->
////                        fadeOut(animationSpec = tween(fadeInDuration)) +
////                                slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(slideInDuration))
////                    NAV_ROUTE_BNB.DISCOVER.routeName ->
////                        fadeOut(animationSpec = tween(fadeInDuration)) +
////                                slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(slideInDuration))
////                    NAV_ROUTE_BNB.UPLOAD.routeName ->
////                        fadeOut(animationSpec = tween(fadeInDuration)) +
////                                slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(slideInDuration))
////                    NAV_ROUTE_BNB.MYPROFILE.routeName ->
////                        fadeOut(animationSpec = tween(fadeInDuration)) +
////                                slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(slideInDuration))
////                    else -> fadeOut(animationSpec = tween(fadeInDuration))
////                }
////            }
////        ) { NotificationScreen(navController) }
//        composable(
//            NAV_ROUTE_BNB.MYPROFILE.routeName,
//            enterTransition = {
//                fadeIn(animationSpec = tween(fadeInDuration)) +
//                        slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(slideInDuration))
//            },
//            exitTransition = {
//                fadeOut(animationSpec = tween(fadeOutDuration)) +
//                        slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(slideOutDuration))
//            }
//        ) {
//
//            ProfileScreen(userId)
//        }
//    }
//}
//
//
//
