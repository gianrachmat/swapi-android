package com.gnrchmt.swapi.networking

import com.gnrchmt.swapi.BuildConfig
import com.gnrchmt.swapi.utils.BASE_URL
import com.gnrchmt.swapi.utils.TIME_OUT
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {
    private val log by lazy {
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    fun mainClient(): IApi {
        val interceptor = Interceptor {
            val request = it.request().newBuilder()
                    // add header here
                .build()
            it.proceed(request)
        }

        return createClient(IApi::class.java, interceptor)
    }

    private fun <T> createClient(clientApi: Class<T>, interceptor: Interceptor? = null): T {
        val client = OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) addInterceptor(log)
            if (interceptor != null) addInterceptor(interceptor)
            connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            readTimeout(TIME_OUT, TimeUnit.SECONDS)
        }

        val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(client.build())
                .build()
        }

        return retrofit.create(clientApi)
    }
}