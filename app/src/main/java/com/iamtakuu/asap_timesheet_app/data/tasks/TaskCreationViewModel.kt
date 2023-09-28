package com.iamtakuu.asap_timesheet_app.data.tasks

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.iamtakuu.asap_timesheet_app.data.rules.Validator

class TaskCreationViewModel : ViewModel() {
    private val TAG = TaskCreationViewModel::class.simpleName

    var taskCreationState = mutableStateOf(TaskCreationState())

    var allValidationsPassed = mutableStateOf(false)

    var creationProcess = mutableStateOf(false)

    fun onEvent(event: TaskCreatedEvent) {
        when (event) {
            is TaskCreatedEvent.IconChanged -> {
                taskCreationState.value = taskCreationState.value.copy(
                    icon = event.icon
                )
                printState()
            }

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

            is TaskCreatedEvent.MinTimeChanged -> {
                taskCreationState.value = taskCreationState.value.copy(
                    minTime = event.minTime
                )
                printState()
            }

            is TaskCreatedEvent.MaxTimeChanged -> {
                taskCreationState.value = taskCreationState.value.copy(
                    maxTime = event.maxTime
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

        val minTimeResult = Validator.validateMinTime(
            minTime = taskCreationState.value.minTime
        )

        val maxTimeResult = Validator.validateMaxTime(
            maxTime = taskCreationState.value.maxTime
        )

        val categoryResult = Validator.validateCategory(
            category = taskCreationState.value.category
        )

        taskCreationState.value = taskCreationState.value.copy(
            startDateError = startDateResult.status,
            endDateError = endDateResult.status,
            minTimeError = minTimeResult.status,
            maxTimeError = maxTimeResult.status,
            categoryError = categoryResult.status,
        )
        Log.d(TAG, "Inside_validateTaskCreationDataWithRules")
        Log.d(TAG, "startDateResult= $startDateResult")
        Log.d(TAG, "endDateResult= $endDateResult")
        Log.d(TAG, "minTimeResult= $minTimeResult")
        Log.d(TAG, "maxTimeResult= $maxTimeResult")
        Log.d(TAG, "categoryResult= $categoryResult")

        allValidationsPassed.value =
                startDateResult.status
                && endDateResult.status
                && minTimeResult.status
                && maxTimeResult.status
                && categoryResult.status
    }

    private fun createTask() {
        creationProcess.value = true
        val icon = taskCreationState.value.icon
        val startDate = taskCreationState.value.startDate
        val endDate = taskCreationState.value.endDate
        val minTime = taskCreationState.value.minTime
        val maxTime = taskCreationState.value.maxTime
        val category = taskCreationState.value.category
        val tags = taskCreationState.value.tags
        val description = taskCreationState.value.description
    }

    private fun printState(){
        Log.d(TAG, "Inside_login_printState")
        Log.d(TAG, taskCreationState.value.toString())
    }
}