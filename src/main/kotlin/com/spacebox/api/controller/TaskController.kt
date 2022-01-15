package com.spacebox.api.controller

import com.spacebox.api.domain.request.AnalysisTaskRequest
import com.spacebox.api.manager.TaskManager
import org.apache.logging.log4j.kotlin.Logging
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/tasks")
class TaskController(private val taskManager: TaskManager) {

    companion object : Logging

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun submitAnalysisTask(@RequestBody task: AnalysisTaskRequest): ResponseEntity<Any> {
        logger.info("Submitting new analysis task with ${task.entries}")
        val entries = taskManager.submitTask(task)
        return ResponseEntity.accepted().build()
    }


}