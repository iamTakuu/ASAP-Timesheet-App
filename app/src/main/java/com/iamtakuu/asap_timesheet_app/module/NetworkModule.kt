package com.iamtakuu.asap_timesheet_app.module

import com.iamtakuu.asap_timesheet_app.data.user.UserDataSource
import com.iamtakuu.asap_timesheet_app.repository.DefualtUserRepository
import com.iamtakuu.asap_timesheet_app.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.postgrest.Postgrest
import javax.inject.Singleton

const val SUPABASEURL = "https://eombpjefbvbngrpcccnw.supabase.co"
const val SUPABASEKEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImVvbWJwamVmYnZibmdycGNjY253Iiwicm9sZSI6ImFub24iLCJpYXQiOjE2OTU3Mjk5MzAsImV4cCI6MjAxMTMwNTkzMH0.f3mneinL6u-nfZA4IuaeI1cs0MgwHrWPKkHqtde6pok"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient{
        return createSupabaseClient(
            supabaseUrl = SUPABASEURL,
            supabaseKey = SUPABASEKEY
        ) {
            this.install(Postgrest)
            this.install(GoTrue)
        }
    }
    @Provides
    @Singleton
    fun provideUserDataSource(client: SupabaseClient): UserDataSource {
        return UserDataSource(client)
    }
    @Provides
    @Singleton
    fun provideUserRepository(dataSource: UserDataSource) : UserRepository{
        return DefualtUserRepository(dataSource)
    }
}