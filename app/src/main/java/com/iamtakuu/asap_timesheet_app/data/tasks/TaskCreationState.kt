package com.iamtakuu.asap_timesheet_app.data.tasks

import android.media.Image

data class TaskCreationState(
    var icon  :String = "",
    //var icon  :Image = ...,
    var startDate  :String = "",
    var endDate  :String = "",
    var time  :Int = 0,
    var category  :String = "",
    var tags  :String = "",
    //var tags  :List<String> = ...,
    var description  :String = "",

    var startDateError :Boolean = false,
    var endDateError : Boolean = false,
    var timeError : Boolean = false,
    var categoryError : Boolean = false,
)