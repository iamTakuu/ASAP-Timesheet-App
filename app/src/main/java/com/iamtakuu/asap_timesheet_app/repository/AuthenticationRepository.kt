package com.iamtakuu.asap_timesheet_app.repository

import com.iamtakuu.asap_timesheet_app.data.user.SupaResult
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {
    fun signIn(email: String, password: String): Flow<SupaResult<Unit>>
    fun signUp(email: String, password: String) : Flow<SupaResult<Unit>>

}