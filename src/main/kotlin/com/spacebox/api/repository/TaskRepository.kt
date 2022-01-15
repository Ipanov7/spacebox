package com.spacebox.api.repository

import com.spacebox.api.domain.common.AnalysisTask

interface TaskRepository {
    fun save(analysisTask: AnalysisTask): AnalysisTask
}