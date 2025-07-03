package com.example.domain.di

import com.example.domain.interactors.RemindWorkManagerInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object WorkManagerInteractorModule {
    @Provides
    fun providesRemindWorkManager() = RemindWorkManagerInteractor()
}