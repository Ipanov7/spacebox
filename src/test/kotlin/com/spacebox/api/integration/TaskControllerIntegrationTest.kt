package com.spacebox.api.integration

import com.spacebox.api.testutils.testAnalysisTaskRequestBuilder
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class TaskControllerIntegrationTest : IntegrationTest() {

    private val basePath = "/api/v1/tasks"

    @Test
    fun `should perform analysis task`() {
        // fixtures
        val entries = listOf(
            "QmZTR5bcpQD7cFgTorqxZDYaew1Wqgfbd2ud9QqGPAkK2V",
            "QmYCvbfNbCwFR45HiNP45rwJgvatpiW38D961L5qAhUM5Y",
            "QmY5heUM5qgRubMDD1og9fhCPA6QdkMp3QCwd4s7gJsyE7"
        )
        // harness
        val taskRequest = testAnalysisTaskRequestBuilder(entries = entries)
        val requestAsJson = mapper.writeValueAsString(taskRequest)

        // when
        mockMvc?.perform(MockMvcRequestBuilders.post(basePath).content(requestAsJson).contentType(MediaType.APPLICATION_JSON))
            ?.andExpect(MockMvcResultMatchers.status().`is`(202))
            ?.andExpect(MockMvcResultMatchers.content().string(""))
    }
}