package com.iamtakuu.asap_timesheet_app.data.tasks

import android.net.Uri

data class TaskCreationState(
    var icon  :Uri = Uri.EMPTY,
    var startDate  :String = "",
    var endDate  :String = "",
    var minTime  :String = "",
    var maxTime  :String = "",
    var category  :String = "",
    var tags  :List<String> = listOf(),
    var description  :String = "",

    var startDateError :Boolean = false,
    var endDateError : Boolean = false,
    var minTimeError : Boolean = false,
    var maxTimeError : Boolean = false,
    var categoryError : Boolean = false,
)