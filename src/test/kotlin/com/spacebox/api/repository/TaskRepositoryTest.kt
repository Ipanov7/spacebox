package com.spacebox.api.repository

import com.spacebox.api.domain.common.AnalysisTask
import com.spacebox.api.domain.entity.AnalysisTaskEntity
import com.spacebox.api.repository.impl.TaskRepositoryImpl
import com.spacebox.api.testutils.testEntryBuilder
import com.spacebox.api.testutils.testEntryEntityBuilder
import io.mockk.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class TaskRepositoryTest {

    private val mockJpaTaskRepository = mockk<JpaTaskRepository>(relaxed = true)
    private val sut = TaskRepositoryImpl(mockJpaTaskRepository)

    @BeforeEach
    fun setUp() {
        clearMocks(mockJpaTaskRepository)
    }

    @Test
    fun `should save an entry`() {
        // fixtures
        val uuid = "some-uuid"
        val entries = listOf(
            testEntryBuilder(hash = "entry-hash-1"),
            testEntryBuilder(hash = "entry-hash-2"),
            testEntryBuilder(hash = "entry-hash-3")
        )
        val output = testEntryBuilder(hash = "output-hash")
        val analysisTask = AnalysisTask(uuid, entries, "some-pub-key", output)

        // harness
        val entryEntities = listOf(
            testEntryEntityBuilder(hash = "entry-hash-1"),
            testEntryEntityBuilder(hash = "entry-hash-2"),
            testEntryEntityBuilder(hash = "entry-hash-3")
        )
        val outputEntity = testEntryEntityBuilder(hash = "output-hash")
        val analysisTaskEntity = AnalysisTaskEntity(analysisTask.uuid, entryEntities, outputEntity, analysisTask.publicKey)

        // given
        val slot = slot<AnalysisTaskEntity>()
        every { mockJpaTaskRepository.save(capture(slot)) } returns analysisTaskEntity

        // when
        val result = sut.save(analysisTask)

        // then
        expectThat(result) isEqualTo analysisTask
        verify(exactly = 1) {
            mockJpaTaskRepository.save(slot.captured)
        }
    }
}