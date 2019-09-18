package com.malenalbc.stormcomics.data.core.api

import com.malenalbc.stormcomics.data.core.extension.md5
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*
import javax.inject.Inject

class MarvelInterceptor @Inject constructor() : Interceptor {

  override fun intercept(chain: Interceptor.Chain): Response {
    val currentTime = System.currentTimeMillis()

    val url = chain.request().url
        .newBuilder()
        .addQueryParameter("ts", "$currentTime")
        .addQueryParameter("apikey", API_KEY)
        .addQueryParameter("hash", "$currentTime$PRIVATE_API_KEY$API_KEY".md5().toLowerCase(Locale.getDefault()))
        .build()

    val request = chain.request().newBuilder()
        .url(url)
        .build()

    return chain.proceed(request)
  }
}

const val API_KEY = "95bcc54e672fb808b02a7dd427dd6ad1"
const val PRIVATE_API_KEY = "3d98b84062b55ea4d8c255c21b212e3ecabe79f3"
