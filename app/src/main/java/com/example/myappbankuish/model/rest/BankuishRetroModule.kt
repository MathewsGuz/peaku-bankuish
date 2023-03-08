package com.example.myappbankuish.model.rest

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Conf retrofit conection
 */
@Module
class BankuishRetroModule {

    private val BASE_URL = "https://api.github.com/search/"

    @Singleton
    @Provides
    fun getClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory((GsonConverterFactory.create()))
            .build()
    }

    @Singleton
    @Provides
    fun getBankuishInterface(retrofit: Retrofit): BankuishService {
        return retrofit.create(BankuishService::class.java)
    }
}