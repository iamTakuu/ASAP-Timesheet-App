package com.iamtakuu.asap_timesheet_app.repository

import com.iamtakuu.asap_timesheet_app.data.signup.SignUpViewModel
import com.iamtakuu.asap_timesheet_app.data.tasks.TaskCreationViewModel
import com.iamtakuu.asap_timesheet_app.data.user.SupaResult
import com.iamtakuu.asap_timesheet_app.data.user.UserDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface UserRepository {
    fun registerUser(user: SignUpViewModel.User) : Flow<SupaResult<Unit>>

    fun createTask(task: TaskCreationViewModel.Task) : Flow<SupaResult<Unit>>
}

class DefaultUserRepository @Inject constructor(
    private val dataSource: UserDataSource
) : UserRepository {
    override fun registerUser(user: SignUpViewModel.User): Flow<SupaResult<Unit>> {
        return dataSource.registerUser(user)
    }

    override fun createTask(task: TaskCreationViewModel.Task): Flow<SupaResult<Unit>> {

        return dataSource.createTask(task)
    }
}