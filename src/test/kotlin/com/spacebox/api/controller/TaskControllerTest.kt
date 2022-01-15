package com.spacebox.api.controller

import com.spacebox.api.manager.TaskManager
import com.spacebox.api.testutils.testAnalysisTaskRequestBuilder
import io.mockk.clearMocks
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isNull

internal class TaskControllerTest {

    private val mockTaskManager = mockk<TaskManager>(relaxed = true)
    private val sut = TaskController(mockTaskManager)

    @BeforeEach
    fun setUp() = clearMocks(mockTaskManager)

    @Test
    fun `should start new task`() {
        // fixtures
        val task = testAnalysisTaskRequestBuilder()

        // given
        justRun { mockTaskManager.submitTask(any()) }

        // when
        val result = sut.submitAnalysisTask(task)

        // then
        expectThat(result) {
            get { statusCode } isEqualTo HttpStatus.ACCEPTED
            get { body }.isNull()
        }
        verify(exactly = 1) {
            mockTaskManager.submitTask(task)
        }
    }
}