package com.example.domain.di

import android.content.Context
import com.example.domain.connectivityobserver.NetworkConnectivityObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ConnectivityObserverModule {
    @Provides
    @Singleton
    fun providesConnectivityObserver(@ApplicationContext context: Context) =
        NetworkConnectivityObserver(context)
}