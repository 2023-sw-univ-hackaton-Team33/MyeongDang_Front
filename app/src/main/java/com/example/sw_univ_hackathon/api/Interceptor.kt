package com.example.sw_univ_hackathon.api
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .header("X-OCR-SECRET", "cFVhS3pxVUtjc0JPbEVsWENrakZDdkhGb21WTGFlVFg=")
            .build()
        return chain.proceed(request)
    }
}
