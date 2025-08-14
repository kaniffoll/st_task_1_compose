package com.example.domain.di

import com.example.domain.utilities.NetworkConnectivityObserver
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val connectivityObserverModule = module {
    single {
        NetworkConnectivityObserver(androidContext())
    }
}