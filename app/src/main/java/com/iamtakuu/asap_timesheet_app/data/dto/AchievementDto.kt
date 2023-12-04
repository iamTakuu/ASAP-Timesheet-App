package com.iamtakuu.asap_timesheet_app.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class AchievementDto(
    @SerialName("id")
    val id: String? = "",

    @SerialName("name")
    val name: String,

    @SerialName("unlocked")
    val unlocked: Boolean
)