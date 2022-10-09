package com.example.yjahz.di

import com.example.yjahz.remote_source.AuthRemoteSource
import com.example.yjahz.remote_source.SellersRemoteSource
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val remoteSourceModules = module {
    factoryOf(::AuthRemoteSource)
    factoryOf(::SellersRemoteSource)
}