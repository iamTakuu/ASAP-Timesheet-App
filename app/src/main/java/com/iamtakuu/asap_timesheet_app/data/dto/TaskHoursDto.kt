package com.iamtakuu.asap_timesheet_app.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TaskHoursDto (
    @SerialName("id")
    val id: String? = "",

    @SerialName("name")
    val name: String,

    @SerialName("hours")
    val hours: Int
)