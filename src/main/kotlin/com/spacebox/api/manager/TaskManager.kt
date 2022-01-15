package com.spacebox.api.manager

import com.spacebox.api.domain.request.AnalysisTaskRequest

interface TaskManager {
    fun submitTask(request: AnalysisTaskRequest)
}