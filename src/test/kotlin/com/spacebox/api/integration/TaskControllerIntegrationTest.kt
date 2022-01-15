package com.spacebox.api.integration

import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class TaskControllerIntegrationTest : IntegrationTest() {

    private val basePath = "/api/v1/contents"

    @Test
    fun `should return tutorial entries`() {

    }
}