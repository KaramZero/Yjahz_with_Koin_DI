package com.example.yjahz.di

import com.example.yjahz.ui.home.adapters.CategoryAdapter
import com.example.yjahz.ui.home.adapters.PopularSellerAdapter
import com.example.yjahz.ui.home.adapters.TrendingSellerAdapter
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val adaptersModule = module {
    factoryOf(::CategoryAdapter)
    factoryOf(::PopularSellerAdapter)
    factoryOf(::TrendingSellerAdapter)
}