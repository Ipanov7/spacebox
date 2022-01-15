package com.spacebox.api.controller

import com.spacebox.api.manager.ContentManager
import com.spacebox.api.testutils.testEntryBuilder
import io.mockk.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class ContentControllerTest {

    private val mockContentManager = mockk<ContentManager>(relaxed = true)
    private val sut = ContentController(mockContentManager)

    @BeforeEach
    fun setUp() = clearMocks(mockContentManager)

    @Test
    fun `should list children entries of object`() {
        // fixtures
        val hash = "some-hash"

        // harness
        val entries = listOf(
            testEntryBuilder(hash = "hash-1"),
            testEntryBuilder(hash = "hash-2"),
            testEntryBuilder(hash = "hash-3")
        )

        // given
        every { mockContentManager.getEntries(any()) } returns entries

        // when
        val result = sut.getEntries(hash)

        // then
        expectThat(result) {
            get { statusCode } isEqualTo HttpStatus.OK
            get { body } isEqualTo entries
        }
        verify(exactly = 1) {
            mockContentManager.getEntries(hash)
        }
    }
}