package com.spacebox.api.integration

import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class ContentControllerIntegrationTest : IntegrationTest() {

    private val basePath = "/api/v1/contents"

    @Test
    fun `should return tutorial entries`() {
        // fixtures
        val hash = "QmYwAPJzv5CZsnA625s3Xf2nemtYgPpHdWEz79ojWnPbdG"

        // harness
        val tutorialEntries = "[{\"hash\":\"QmZTR5bcpQD7cFgTorqxZDYaew1Wqgfbd2ud9QqGPAkK2V\",\"size\":1677,\"type\":\"FILE\"}," +
            "{\"hash\":\"QmYCvbfNbCwFR45HiNP45rwJgvatpiW38D961L5qAhUM5Y\",\"size\":189,\"type\":\"FILE\"}," +
            "{\"hash\":\"QmY5heUM5qgRubMDD1og9fhCPA6QdkMp3QCwd4s7gJsyE7\",\"size\":311,\"type\":\"FILE\"}," +
            "{\"hash\":\"QmdncfsVm2h5Kqq9hPmU7oAVX2zTSVP3L869tgTbPYnsha\",\"size\":1717,\"type\":\"FILE\"}," +
            "{\"hash\":\"QmPZ9gcCEpqKTo6aq61g2nXGUhM4iCL3ewB6LDXZCtioEB\",\"size\":1091,\"type\":\"FILE\"}," +
            "{\"hash\":\"QmTumTjvcYCAvRRwQ8sDRxh8ezmrcr88YFU7iYNroGGTBZ\",\"size\":1016,\"type\":\"FILE\"}]"

        // when
        mockMvc?.perform(MockMvcRequestBuilders.get("$basePath/$hash").accept(MediaType.APPLICATION_JSON))
            ?.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            ?.andExpect(MockMvcResultMatchers.status().`is`(200))
            ?.andExpect(MockMvcResultMatchers.content().string(tutorialEntries))

    }
}