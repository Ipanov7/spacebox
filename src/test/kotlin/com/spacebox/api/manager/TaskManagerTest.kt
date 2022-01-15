package com.spacebox.api.manager

import com.spacebox.api.domain.entity.AnalysisTaskEntity
import com.spacebox.api.manager.impl.TaskManagerImpl
import com.spacebox.api.repository.JpaTaskRepository
import com.spacebox.api.testutils.testAnalysisTaskRequestBuilder
import com.spacebox.api.testutils.testEntryBuilder
import com.spacebox.api.testutils.testEntryEntityBuilder
import io.mockk.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class TaskManagerTest {
    private val mockJpaTaskRepository = mockk<JpaTaskRepository>(relaxed = true)
    private val mockContentManager = mockk<ContentManager>(relaxed = true)
    private val sut = TaskManagerImpl(mockJpaTaskRepository, mockContentManager)

    @BeforeEach
    fun setUp() = clearMocks(mockJpaTaskRepository, mockContentManager)

    @Test
    fun `should submit task`() {
        // fixtures
        val cids = listOf("cid-1", "cid-2", "cid-3")
        val taskRequest = testAnalysisTaskRequestBuilder(entries = cids)

        // harness
        val entry = testEntryBuilder(hash = "some-hash")
        val entryEntities = listOf(entry, entry, entry).map { testEntryEntityBuilder(it) }
        val outputEntry = testEntryBuilder(hash = "output-hash")
        val outputEntryEntity = testEntryEntityBuilder(outputEntry)
        val analysisOutput = "Some analysis insights = {...}"
        val task = AnalysisTaskEntity(
            uuid = taskRequest.uuid,
            entries = entryEntities,
            output = outputEntryEntity,
            publicKey = taskRequest.publicKey
        )

        // given
        every { mockContentManager.getEntry(any()) } returns entry
        every { mockContentManager.upload(any()) } returns outputEntry
        val slot = slot<AnalysisTaskEntity>()
        every { mockJpaTaskRepository.save(capture(slot)) } returns task

        // when
        sut.submitTask(taskRequest)

        // then
        expectThat(slot.captured) {
            get { uuid } isEqualTo task.uuid
            get { publicKey } isEqualTo task.publicKey
        }
        verifyOrder {
                mockContentManager.getEntry(cids[0])
                mockContentManager.getEntry(cids[1])
                mockContentManager.getEntry(cids[2])
            mockContentManager.upload(analysisOutput)
            mockJpaTaskRepository.save(slot.captured)
        }

    }
}