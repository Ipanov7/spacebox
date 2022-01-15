package com.spacebox.api.manager.impl

import com.spacebox.api.client.util.toTaskEntity
import com.spacebox.api.domain.common.AnalysisTask
import com.spacebox.api.domain.common.Entry
import com.spacebox.api.domain.request.AnalysisTaskRequest
import com.spacebox.api.manager.ContentManager
import com.spacebox.api.manager.TaskManager
import com.spacebox.api.repository.JpaTaskRepository
import org.apache.logging.log4j.kotlin.Logging
import org.springframework.stereotype.Component

@Component
class TaskManagerImpl(
    private val jpaTaskRepository: JpaTaskRepository,
    private val contentManager: ContentManager
) : TaskManager {
    companion object : Logging

    override fun submitTask(request: AnalysisTaskRequest) {
        logger.debug("Start analysis task {uuid=${request.uuid}}")

        val entries = request.entries.map { contentManager.getEntry(it) }
        val analysisOutput = executeTask(request, entries)

        logger.debug("Encrypting analysis with key {pub_key=${request.publicKey}}")
        val encryptedOutput = analysisOutput

        logger.debug("Uploading encrypted analysis output on IPFS...")
        val output = contentManager.upload(encryptedOutput)
        val task = AnalysisTask(uuid = request.uuid, entries = entries, publicKey = request.publicKey, output = output)

        logger.debug("Saving analysis task {uuid=${task.uuid}} with output {hash=${task.output.hash}}")
        jpaTaskRepository.save(toTaskEntity(task))
    }


    private fun executeTask(request: AnalysisTaskRequest, entries: List<Entry>): String {
        logger.debug("Executing analysis task {uuid=${request.uuid}}, downloading entries from IPFS {cids=${request.entries}}...")
        return "Some analysis insights = {...}"
    }

}