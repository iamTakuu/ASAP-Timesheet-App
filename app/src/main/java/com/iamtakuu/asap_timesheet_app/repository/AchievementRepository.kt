package com.iamtakuu.asap_timesheet_app.repository

import com.iamtakuu.asap_timesheet_app.data.dto.AchievementDto
import com.iamtakuu.asap_timesheet_app.data.dto.TaskHoursDto

interface AchievementRepository {
    suspend fun getAchievements(): List<AchievementDto>
    suspend fun getTaskHours(): List<TaskHoursDto>
}