package com.iamtakuu.asap_timesheet_app.data.login

sealed class TaskCreatedEvent {
    data class StartDateChanged(val startDate:String): TaskCreatedEvent()
    data class EndDateChanged(val endDate:String): TaskCreatedEvent()
    data class TimeChanged(val time:Int): TaskCreatedEvent()
    data class CategoryChanged(val category: String) : TaskCreatedEvent()
    data class TagsChanged(val tags: String) : TaskCreatedEvent()
    data class DescriptionChanged(val description: String) : TaskCreatedEvent()
    object TaskCreationButtonClicked : TaskCreatedEvent()
}