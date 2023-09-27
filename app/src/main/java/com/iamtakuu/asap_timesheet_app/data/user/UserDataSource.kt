package com.iamtakuu.asap_timesheet_app.data.user

import com.iamtakuu.asap_timesheet_app.data.signup.SignUpViewModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val supabaseClient: SupabaseClient
) {
    fun registerUser(user: SignUpViewModel.User) : Flow<SupaResult<Unit>> {
        return  flow{
            emit(SupaResult.Loading)
            try {
                supabaseClient
                    .gotrue
                    .signUpWith(Email){
                        email = user.email
                        password = user.password
                    }
                emit(SupaResult.Success(Unit))
            }catch (e: Exception){
                emit(SupaResult.Error(e.message))
            }

        }

    }
}

sealed class SupaResult<out R> {
    data class Success<out R>(val data: R): SupaResult<R>()
    data class Error(val message: String?): SupaResult<Nothing>()
    object Loading : SupaResult<Nothing>()

}