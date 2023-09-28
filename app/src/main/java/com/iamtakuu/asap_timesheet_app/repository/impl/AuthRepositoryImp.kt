package com.iamtakuu.asap_timesheet_app.repository.impl

import android.content.Context
import com.iamtakuu.asap_timesheet_app.data.user.SupaResult
import com.iamtakuu.asap_timesheet_app.repository.AuthenticationRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor(
    private val goTrue: GoTrue,
    @ApplicationContext private val context: Context
): AuthenticationRepository {
    override fun signIn(email: String, password: String): Flow<SupaResult<Unit>> {
        return flow {
            emit(SupaResult.Loading)
            try {
                goTrue.loginWith(Email) {
                    this.email = email
                    this.password = password
                }
                emit(SupaResult.Success(Unit))
            } catch (e: Exception) {
                emit(SupaResult.Error(e.message))
            }
        }
    }

    override fun signUp(email: String, password: String): Flow<SupaResult<Unit>> {
        return  flow{
            emit(SupaResult.Loading)
            try {
                goTrue.signUpWith(Email) {
                        this.email = email
                        this.password = password
                    }
                emit(SupaResult.Success(Unit))
            }catch (e: Exception){
                emit(SupaResult.Error(e.message))
            }

        }
    }
}