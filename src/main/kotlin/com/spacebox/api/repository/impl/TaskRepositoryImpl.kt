package com.spacebox.api.repository.impl

import com.spacebox.api.client.util.toTask
import com.spacebox.api.client.util.toTaskEntity
import com.spacebox.api.domain.common.AnalysisTask
import com.spacebox.api.repository.JpaTaskRepository
import com.spacebox.api.repository.TaskRepository
import org.springframework.stereotype.Component

@Component
class TaskRepositoryImpl(private val jpaTaskRepository: JpaTaskRepository) : TaskRepository {
    override fun save(analysisTask: AnalysisTask): AnalysisTask {
        val saved = jpaTaskRepository.save(toTaskEntity(analysisTask))
        return toTask(saved)
    }
}