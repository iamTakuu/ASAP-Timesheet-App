package com.iamtakuu.asap_timesheet_app.data.tasks

import android.net.Uri

sealed class TaskCreatedEvent {
    data class IconChanged(val icon:Uri): TaskCreatedEvent()
    data class StartDateChanged(val startDate:String): TaskCreatedEvent()
    data class EndDateChanged(val endDate:String): TaskCreatedEvent()
    data class MinTimeChanged(val minTime:String): TaskCreatedEvent()
    data class MaxTimeChanged(val maxTime:String): TaskCreatedEvent()
    data class CategoryChanged(val category: String) : TaskCreatedEvent()
    data class TagsChanged(val tags: List<String>) : TaskCreatedEvent()
    data class DescriptionChanged(val description: String) : TaskCreatedEvent()
    object TaskCreationButtonClicked : TaskCreatedEvent()
}