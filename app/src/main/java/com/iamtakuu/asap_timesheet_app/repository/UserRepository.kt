package com.iamtakuu.asap_timesheet_app.repository

//import com.iamtakuu.asap_timesheet_app.data.signup.SignUpViewModel
//import com.iamtakuu.asap_timesheet_app.data.user.SupaResult
//import com.iamtakuu.asap_timesheet_app.data.user.UserDataSource
//import kotlinx.coroutines.flow.Flow
//import javax.inject.Inject
//
//interface UserRepository {
//    fun registerUser(user: SignUpViewModel.User) : Flow<SupaResult<Unit>>
//}
//
//class DefualtUserRepository @Inject constructor(
//    private val dataSource: UserDataSource
//) : UserRepository {
//    override fun registerUser(user: SignUpViewModel.User): Flow<SupaResult<Unit>> {
//        return dataSource.registerUser(user)
//    }
//}