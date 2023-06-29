package com.example.sw_univ_hackathon

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.sw_univ_hackathon.ui.route.NavigationGraph
import com.example.sw_univ_hackathon.ui.theme.SW_univ_hackathonTheme
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SW_univ_hackathonTheme {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    TedPermission.create()
                        .setPermissionListener(object: PermissionListener {

                            override fun onPermissionGranted() {
                            }
                            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                            }
                        })
                        .setDeniedMessage("카메라 권한을 허용해주세요.")
                        .setPermissions(Manifest.permission.CAMERA)
                        .check()
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    TedPermission.create()
                        .setPermissionListener(object: PermissionListener {

                            override fun onPermissionGranted() {
                            }
                            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                            }
                        })
                        .setDeniedMessage("저장소 쓰기 권한을 허용해주세요.")
                        .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .check()
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    TedPermission.create()
                        .setPermissionListener(object: PermissionListener {

                            override fun onPermissionGranted() {
                            }
                            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                            }
                        })
                        .setDeniedMessage("저장소 읽기 권한을 허용해주세요.")
                        .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .check()
                }
                NavScreen()

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SW_univ_hackathonTheme {
        Greeting("Android")
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavScreen() {
    val navController = rememberNavController()
    NavigationGraph(navController = navController)
}