package com.example.sw_univ_hackathon.ui.card

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.example.sw_univ_hackathon.R
import com.example.sw_univ_hackathon.ui.data.RelatedChannelDto
import com.example.sw_univ_hackathon.ui.route.NAV_ROUTE
import com.example.sw_univ_hackathon.ui.theme.MDGray
import com.example.sw_univ_hackathon.util.ShowProgressBar
import com.example.sw_univ_hackathon.util.bounceClick


@Composable
fun RelatedChannelCard(
    navHostController: NavHostController,
    data: RelatedChannelDto
) {

////////////

    val isLoading = remember { mutableStateOf(true) }
    val isWebView = remember { mutableStateOf(false) }

    if (isWebView.value) {
        Box (
            modifier = Modifier.padding(top=5.dp)
                )
        {
            AndroidView(
                factory = {
                    WebView(it)
                        .apply {
                            layoutParams = ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT
                            )

                            webViewClient = object : WebViewClient() {
                                override fun onPageFinished(view: WebView?, url: String?) {
                                    super.onPageFinished(view, url)
                                    isLoading.value = false
                                }
                            }
                        }
                }, update = { it.loadUrl(data.link) }
            )
            if (isLoading.value) ShowProgressBar()
        }

    } else {


        //////////////////
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(horizontal = 20.dp)
                .bounceClick {
//                    navHostController.navigate(NAV_ROUTE.WEBVIEW.routeName + "/" + data.link)
                             isWebView.value = true
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text(
                    text = data.title,
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontWeight = FontWeight(700),
                    modifier = Modifier
                        .fillMaxWidth(),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = data.company,
                    color = Color(0xFF727272),
                    fontSize = 13.sp,
                    fontWeight = FontWeight(300),
                    modifier = Modifier
                        .fillMaxWidth(),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.ic_chevron_right),
                contentDescription = null,
                modifier = Modifier
                    .bounceClick {

                    }
            )
        }
        Box(modifier = Modifier.fillMaxWidth().height(0.6.dp).background(MDGray).alpha(0.4f))
    }
}