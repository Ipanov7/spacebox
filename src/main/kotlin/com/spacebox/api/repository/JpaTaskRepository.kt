package com.spacebox.api.repository

import com.spacebox.api.domain.entity.AnalysisTaskEntity
import org.springframework.data.repository.CrudRepository

interface JpaTaskRepository : CrudRepository<AnalysisTaskEntity, String>