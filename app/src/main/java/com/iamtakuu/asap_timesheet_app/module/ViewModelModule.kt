package com.iamtakuu.asap_timesheet_app.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object ViewModelModule {
    @Provides
    fun provideUserViewModel(): UserViewModel{
        return UserViewModel()
    }


}