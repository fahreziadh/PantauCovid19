package dev.ajiex.pantaucovid.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit

class ApiFactory() {
    companion object {
        val BASE_URL = "https://api.covid19api.com/"
    }

    private val interceptor = Interceptor { chain ->
        val newUrl = chain.request().url.newBuilder().build()
        val newRequest = chain.request().newBuilder()
            .url(newUrl)
            .build()

        chain.proceed(newRequest)
    }
    var logging = HttpLoggingInterceptor()

    private val client = OkHttpClient().newBuilder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .addInterceptor(logging.setLevel(HttpLoggingInterceptor.Level.BASIC))
        .build()

    fun main(): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val api: Api = main().create(Api::class.java)

}