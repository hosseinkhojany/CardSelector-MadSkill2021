package com.example.myapplication.net

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
/**
 * Observes, modifies, and potentially short-circuits requests going out and the corresponding
 * responses coming back in. Typically interceptors add, remove, or transform headers on the request
 * or response.
 */
class HttpRequestInterceptor : Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response {
    val originalRequest = chain.request()
    val request = originalRequest.newBuilder().url(originalRequest.url).build()
    Timber.d(request.toString())
    return chain.proceed(request)
  }
}
