package com.iamtakuu.asap_timesheet_app.data.tasks

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iamtakuu.asap_timesheet_app.data.rules.Validator
import com.iamtakuu.asap_timesheet_app.data.user.SupaResult
import com.iamtakuu.asap_timesheet_app.repository.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import javax.inject.Inject

@HiltViewModel
class TaskCreationViewModel @Inject constructor(
    private val repository: AuthenticationRepository
): ViewModel(){
    private val TAG = TaskCreationViewModel::class.simpleName

    var taskCreationState = mutableStateOf(TaskCreationState())
    var allValidationsPassed = mutableStateOf(false)

    var creationProcess = mutableStateOf(false)
    var creationError = mutableStateOf(false)

    private val _uiState = MutableStateFlow<SupaResult<*>>(SupaResult.Loading)
    val uiState: StateFlow<SupaResult<*>>
        get() = _uiState

    fun onEvent(event: TaskCreatedEvent, onCreateTaskClick: () -> Unit) {
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
                if (allValidationsPassed.value) {
                    createTask()
                    observeState(onCreateTaskClick)
                }
            }
        }

        validateTaskCreationDataWithRules(onCreateTaskClick)
    }

    private fun validateTaskCreationDataWithRules(onCreateTaskClick: () -> Unit) {
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

        Log.d(TAG, "Inside_validateTaskCreationDataWithRules")
        Log.d(TAG, "startDateResult= $startDateResult")
        Log.d(TAG, "endDateResult= $endDateResult")
        Log.d(TAG, "minTimeResult= $minTimeResult")
        Log.d(TAG, "maxTimeResult= $maxTimeResult")
        Log.d(TAG, "categoryResult= $categoryResult")

        taskCreationState.value = taskCreationState.value.copy(
            startDateError = startDateResult.status,
            endDateError = endDateResult.status,
            minTimeError = minTimeResult.status,
            maxTimeError = maxTimeResult.status,
            categoryError = categoryResult.status,
        )

        allValidationsPassed.value =
                startDateResult.status
                && endDateResult.status
                && minTimeResult.status
                && maxTimeResult.status
                && categoryResult.status

        if (allValidationsPassed.value) {
            createTask()
            observeState(onCreateTaskClick)
        }
    }

    //Temporary data class
    @Serializable
    data class Task(
        //val id: Int = 0,
        var icon  : String = "",
        var startDate  :String = "",
        var endDate  :String = "",
        var minTime  :String = "",
        var maxTime  :String = "",
        var category  :String = "",
        var tags  :List<String> = listOf(),
        var description  :String = ""
    )

    private fun createTask() {
        viewModelScope.launch {
            Log.e("", uiState.toString())
            val task = Task(
                icon = taskCreationState.value.icon.toString(),
                startDate = taskCreationState.value.startDate,
                endDate = taskCreationState.value.endDate,
                minTime = taskCreationState.value.minTime,
                maxTime = taskCreationState.value.maxTime,
                category = taskCreationState.value.category,
                tags = taskCreationState.value.tags,
                description = taskCreationState.value.description
            )
        }
    }

    private fun observeState(onCreateTaskClick: () -> Unit) {
        viewModelScope.launch {
            uiState.collectLatest { data ->
                when(data) {
                    is SupaResult.Error -> {
                        Log.e("CreateTaskVM", "Message ${data.message}")
                    }
                    is SupaResult.Loading -> {
                        creationProcess.value = true
                        Log.e("CreateTaskVM", "Loading...")
                    }
                    is SupaResult.Success -> {
                        Log.e("CreateTaskVM", "Epic ${data.data}")
                        //ApplicationRouter.navigateTo(Screen.TaskCreationScreen)
                    }
                }
            }
        }
    }

    private fun printState(){
        Log.d(TAG, "Inside_task_creation_printState")
        Log.d(TAG, taskCreationState.value.toString())
    }
}