package com.spacebox.api.manager

import com.spacebox.api.client.IpfsClient
import com.spacebox.api.domain.common.Entry
import com.spacebox.api.domain.common.Type
import com.spacebox.api.domain.ipfs.ListResult
import com.spacebox.api.domain.ipfs.Object
import com.spacebox.api.manager.impl.ContentManagerImpl
import com.spacebox.api.testutils.testEntryBuilder
import com.spacebox.api.testutils.testFileStatsBuilder
import com.spacebox.api.testutils.testLinkBuilder
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class ContentManagerTest {
    private val mockIpfsClient = mockk<IpfsClient>(relaxed = true)
    private val sut = ContentManagerImpl(mockIpfsClient)

    @BeforeEach
    fun setUp() = clearMocks(mockIpfsClient)

    @Test
    fun `should return children links of object`() {
        // fixtures
        val hash = "some-hash"

        // harness
        val links = listOf(
            testLinkBuilder(name = "link-1", hash = "hash-1"),
            testLinkBuilder(name = "link-2", hash = "hash-2"),
            testLinkBuilder(name = "link-3", hash = "hash-3")
        )
        val listResult = ListResult(
            objects = listOf(
                Object(
                    hash = "parent-hash",
                    links = links
                )
            )
        )

        val entries = listOf(
            testEntryBuilder(hash = "hash-1"),
            testEntryBuilder(hash = "hash-2"),
            testEntryBuilder(hash = "hash-3")
        )

        // given
        every { mockIpfsClient.list(any()) } returns listResult

        // when
        val result = sut.getEntries(hash)

        // then
        expectThat(result) isEqualTo entries

        verify(exactly = 1) {
            mockIpfsClient.list(hash)
        }

    }

    @Test
    fun `should return file stats`() {
        // fixtures
        val hash = "hash"

        // harness
        val fileStats = testFileStatsBuilder(hash = hash)
        val entry = Entry(hash = hash, type = Type.FILE, size = fileStats.size)

        // given
        every { mockIpfsClient.fileStats(any()) } returns fileStats

        // when
        val result = sut.getEntry(hash)

        // then
        expectThat(result) isEqualTo entry
        verify(exactly = 1) {
            mockIpfsClient.fileStats("/ipfs/$hash")
        }
    }

    @Test
    fun `should upload content`() {
        // fixtures
        val content = "some content to upload"

        // harness
        val entry = testEntryBuilder(hash = "some-hash")

        // given
        every { mockIpfsClient.add(any()) } returns entry

        // when
        val result = sut.upload(content)

        // then
        expectThat(result) isEqualTo entry
        verify(exactly = 1) {
            mockIpfsClient.add(content.toByteArray())
        }

    }

}