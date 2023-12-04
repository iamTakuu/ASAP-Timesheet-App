package com.iamtakuu.asap_timesheet_app.repository

import com.iamtakuu.asap_timesheet_app.data.dto.AchievementDto

interface AchievementRepository {
    suspend fun getAchievements(): List<AchievementDto>
}