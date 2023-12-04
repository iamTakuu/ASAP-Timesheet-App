package com.iamtakuu.asap_timesheet_app.repository

import com.iamtakuu.asap_timesheet_app.repository.impl.AchievementRepositoryImp
import com.iamtakuu.asap_timesheet_app.repository.impl.AuthRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindAuthRepostiory(impl: AuthRepositoryImp): AuthenticationRepository
    @Binds
    abstract fun bindAchievementRepository(impl: AchievementRepositoryImp) : AchievementRepository
//    @Binds
//    abstract fun bindDailyGoalRepository(impl: DailyGoalRepositoryImpl): DailyGoalRepository
//    @Binds
//    abstract fun bindTimesheetRepository(impl: TimesheetRepositoryImpl): TimesheetRepository
//    @Binds
//    abstract fun bindCategoryRepository(impl: CategoryRepositoryImpl): CategoryRepository
}