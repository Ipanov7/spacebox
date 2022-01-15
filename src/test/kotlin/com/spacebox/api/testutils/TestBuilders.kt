package com.spacebox.api.testutils

import com.spacebox.api.domain.common.CID
import com.spacebox.api.domain.common.Entry
import com.spacebox.api.domain.common.Type
import com.spacebox.api.domain.entity.EntryEntity
import com.spacebox.api.domain.ipfs.FileStatsResult
import com.spacebox.api.domain.ipfs.Link
import com.spacebox.api.domain.request.AnalysisTaskRequest
import java.util.*

fun testLinkBuilder(
    name: String,
    hash: CID,
    size: Long = 4,
    type: Int = 2
) = Link(
    name = name,
    hash = hash,
    size = size,
    type = type,
)

fun testEntryBuilder(
    hash: CID,
    size: Long = 4,
    type: Type = Type.FILE
) = Entry(
    hash = hash,
    size = size,
    type = type,
)

fun testEntryEntityBuilder(
    entry: Entry
) = EntryEntity(
    hash = entry.hash,
    size = entry.size,
    type = entry.type
)

fun testEntryEntityBuilder(
    hash: CID,
    size: Long = 4,
    type: Type = Type.FILE
) = EntryEntity(
    hash = hash,
    size = size,
    type = type,
)

fun testFileStatsBuilder(
    hash: CID = "hash-1",
    size: Long = 1024,
    type: String = "file"
) = FileStatsResult(
    hash, size, type
)

fun testAnalysisTaskRequestBuilder(
    uuid: String = UUID.randomUUID().toString(),
    entries: List<CID> = listOf("cid-1", "cid-2", "cid-3"),
    publicKey: String = "pubkey"
) = AnalysisTaskRequest(
    uuid = uuid,
    entries = entries,
    publicKey = publicKey
)