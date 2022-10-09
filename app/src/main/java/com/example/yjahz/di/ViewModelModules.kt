package com.example.yjahz.di

import com.example.yjahz.ui.home.HomeViewModel
import com.example.yjahz.ui.log_in.LogInViewModel
import com.example.yjahz.ui.sign_up.SignUpViewModel
import com.example.yjahz.ui.welcome.WelcomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val viewModelModules = module {
    viewModelOf(::WelcomeViewModel)
    viewModelOf(::SignUpViewModel)
    viewModelOf(::LogInViewModel)
    viewModelOf(::HomeViewModel)
}