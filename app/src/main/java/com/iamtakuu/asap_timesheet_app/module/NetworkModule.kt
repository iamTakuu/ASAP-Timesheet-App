package com.iamtakuu.asap_timesheet_app.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.serializer.KotlinXSerializer
import kotlinx.serialization.json.Json
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
            defaultSerializer = KotlinXSerializer(Json {
                ignoreUnknownKeys = true
            })
            this.install(Postgrest)
            this.install(GoTrue)
        }
    }
    @Provides
    @Singleton
    fun providePostgrest(client: SupabaseClient): Postgrest {
        return client.postgrest
    }

    @Provides
    @Singleton
    fun provideGoTrue(client: SupabaseClient): GoTrue {
        return client.gotrue
    }
}