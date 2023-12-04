package com.iamtakuu.asap_timesheet_app.data.statistics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iamtakuu.asap_timesheet_app.data.model.Achievement
import com.iamtakuu.asap_timesheet_app.data.model.TaskHours
import com.iamtakuu.asap_timesheet_app.repository.AchievementRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AchievementViewModel @Inject constructor(
    private val repository: AchievementRepository
) : ViewModel(){
    private val TAG = AchievementViewModel::class.simpleName

    private val _achievementList = MutableStateFlow<List<Achievement>>(listOf())
    val achievementList = _achievementList

    private val _hoursList = MutableStateFlow<List<TaskHours>>(listOf())
    val hoursList = _hoursList

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading

    init {
        getAchievements()
        getTaskHours()
    }
    private fun getTaskHours(){
        viewModelScope.launch {
            val taskHoursDto = repository.getTaskHours()
            _hoursList.emit(taskHoursDto.map { it ->
                TaskHours(
                    id = it.id ?: "",
                    name = it.name,
                    hours = it.hours
                )
            })
            _isLoading.emit(false)
        }
    }
    private fun getAchievements(){
        viewModelScope.launch {
            val achievementDtos = repository.getAchievements()
            _achievementList.emit(achievementDtos.map { it ->
                Achievement(
                    id = it.id ?: "",
                    name = it.name,
                    painterID = it.painterID
                )
            })
            _isLoading.emit(false)
        }
    }
}