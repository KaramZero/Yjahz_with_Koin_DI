package com.example.yjahz.di

import com.example.yjahz.network.YogahezAuthApiServices
import com.example.yjahz.network.YogahezSellersApiServices
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit


val apiModule = module {
    singleOf(::provideSellersApiService)
    singleOf(::provideAuthApiService)
}

fun provideSellersApiService(retrofit: Retrofit): YogahezSellersApiServices = retrofit.create(
    YogahezSellersApiServices::class.java
)

fun provideAuthApiService(retrofit: Retrofit): YogahezAuthApiServices = retrofit.create(
    YogahezAuthApiServices::class.java
)
