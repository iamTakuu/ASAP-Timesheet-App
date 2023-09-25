package com.iamtakuu.asap_timesheet_app.data.tasks

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.iamtakuu.asap_timesheet_app.data.rules.Validator

class TaskCreationViewModel : ViewModel() {
    private val TAG = TaskCreationViewModel::class.simpleName

    var taskCreationState = mutableStateOf(TaskCreationState())

    var allValidationsPassed = mutableStateOf(false)

    fun onEvent(event: TaskCreatedEvent) {
        when (event) {
            is TaskCreatedEvent.StartDateChanged -> {
                taskCreationState.value = taskCreationState.value.copy(
                    startDate = event.startDate
                )
                printState()
            }

            is TaskCreatedEvent.EndDateChanged -> {
                taskCreationState.value = taskCreationState.value.copy(
                    endDate = event.endDate
                )
                printState()
            }

            is TaskCreatedEvent.TimeChanged -> {
                taskCreationState.value = taskCreationState.value.copy(
                    time = event.time
                )
                printState()
            }

            is TaskCreatedEvent.CategoryChanged -> {
                taskCreationState.value = taskCreationState.value.copy(
                    category = event.category
                )
                printState()
            }

            is TaskCreatedEvent.TagsChanged -> {
                taskCreationState.value = taskCreationState.value.copy(
                    tags = event.tags
                )
                printState()
            }

            is TaskCreatedEvent.DescriptionChanged -> {
                taskCreationState.value = taskCreationState.value.copy(
                    description = event.description
                )
                printState()
            }

            // CREATE TASK HERE
            is TaskCreatedEvent.TaskCreationButtonClicked -> {
                //login()
                printState()
            }
        }

        validateTaskCreationDataWithRules()
    }

    private fun validateTaskCreationDataWithRules() {
        val startDateResult = Validator.validateStartDate(
            startDate = taskCreationState.value.startDate
        )

        val endDateResult = Validator.validateEndDate(
            endDate = taskCreationState.value.endDate
        )

        val timeResult = Validator.validateTime(
            time = taskCreationState.value.time
        )

        val categoryResult = Validator.validateCategory(
            category = taskCreationState.value.category
        )

        /*taskCreationState.value = taskCreationState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )
        Log.d(TAG, "Inside_validateDataWithRules")
        Log.d(TAG, "emailResult= $emailResult")
        Log.d(TAG, "passwordResult= $passwordResult")
        allValidationsPassed.value = emailResult.status && passwordResult.status*/

    }

    private fun printState(){
        Log.d(TAG, "Inside_login_printState")
        Log.d(TAG, taskCreationState.value.toString())
    }
}