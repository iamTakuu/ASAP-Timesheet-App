package com.iamtakuu.asap_timesheet_app.data.tasks.model

import androidx.annotation.DrawableRes

data class Task(
val id: String,
val title: String,
val subtitle: String? = null,
@DrawableRes val imageId: Int,
@DrawableRes val imageThumbId: Int
)



