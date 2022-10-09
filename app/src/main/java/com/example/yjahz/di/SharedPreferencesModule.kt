package com.example.yjahz.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module


val sharedPreferencesModule = module {
    factoryOf(::provideSharedPreferences)
}

fun provideSharedPreferences(application: Application): SharedPreferences {
    return application.getSharedPreferences("user", Context.MODE_PRIVATE)
}