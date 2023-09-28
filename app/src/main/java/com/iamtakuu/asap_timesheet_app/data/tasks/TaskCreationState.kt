package com.iamtakuu.asap_timesheet_app.data.tasks

data class TaskCreationState(
    var icon  :String = "",
    //var icon  :Image = ...,
    var startDate  :String = "",
    var endDate  :String = "",
    var minTime  :String = "",
    var maxTime  :String = "",
    var category  :String = "",
    var tags  :String = "",
    //var tags  :List<String> = ...,
    var description  :String = "",

    var startDateError :Boolean = false,
    var endDateError : Boolean = false,
    var minTimeError : Boolean = false,
    var maxTimeError : Boolean = false,
    var categoryError : Boolean = false,
)