package com.colby.bloom_number_facts.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface NumbersDataSource {
    @GET("{range}")
    suspend fun getNumbers(@Path("range") range: String): Map<String, String>

    companion object {
        private const val BASE_URL = "http://numbersapi.com/"
        private val logging = HttpLoggingInterceptor()

        fun create(): NumbersDataSource {
            logging.level = HttpLoggingInterceptor.Level.BASIC
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build().create(NumbersDataSource::class.java)
        }
    }
}