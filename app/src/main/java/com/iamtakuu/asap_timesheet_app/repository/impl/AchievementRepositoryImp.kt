package com.iamtakuu.asap_timesheet_app.repository.impl

import com.iamtakuu.asap_timesheet_app.data.dto.AchievementDto
import com.iamtakuu.asap_timesheet_app.data.dto.TaskHoursDto
import com.iamtakuu.asap_timesheet_app.repository.AchievementRepository
import io.github.jan.supabase.postgrest.Postgrest
import javax.inject.Inject

const val ACHIEVEMENTS_TABLE_ID = "achievements"
const val TASKHOURS_TABLE_ID = "task_hours"
class AchievementRepositoryImp @Inject constructor(
    private val postgrest: Postgrest,
) : AchievementRepository {
    override suspend fun getAchievements(): List<AchievementDto> {
        return postgrest[ACHIEVEMENTS_TABLE_ID].select().decodeList()
    }

    override suspend fun getTaskHours(): List<TaskHoursDto> {
        return postgrest[TASKHOURS_TABLE_ID].select().decodeList()

    }
}