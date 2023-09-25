package com.iamtakuu.asap_timesheet_app.data.tasks

data class TaskCreationState(
    var startDate  :String = "",
    var endDate  :String = "",
    var time  :Int = 0,
    var category  :String = "",
    var tags  :String = "",
    var description  :String = "",

    var startDateError :Boolean = false,
    var endDateError : Boolean = false,
    var timeError : Boolean = false,
    var categoryError : Boolean = false,
)