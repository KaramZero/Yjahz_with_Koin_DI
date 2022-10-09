package com.example.yjahz.di

import com.example.yjahz.remote_source.AuthRemoteSource
import com.example.yjahz.remote_source.SellersRemoteSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val remoteSourceModule = module {
    singleOf(::AuthRemoteSource)
    singleOf(::SellersRemoteSource)
}