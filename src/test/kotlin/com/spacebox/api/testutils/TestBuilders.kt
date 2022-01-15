package com.spacebox.api.testutils

import com.spacebox.api.domain.CID
import com.spacebox.api.domain.common.Entry
import com.spacebox.api.domain.common.Type
import com.spacebox.api.domain.common.User
import com.spacebox.api.domain.entity.EntryEntity
import com.spacebox.api.domain.entity.UserEntity
import com.spacebox.api.domain.ipfs.FileStatsResult
import com.spacebox.api.domain.ipfs.Link
import com.spacebox.api.domain.request.AddFavoriteRequest
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

fun testUserBuilder(
    id: Long = 1L,
    name: String = "Loris",
    favorites: MutableSet<Entry> = mutableSetOf(
        testEntryBuilder("hash-1"),
        testEntryBuilder("hash-2"),
        testEntryBuilder("hash-3"),
    )
) = User(
    id = id,
    name = name,
    favorites = favorites
)

fun testUserEntityBuilder(
    id: Long = 1L,
    name: String = "Loris",
    favorites: Set<EntryEntity> = setOf(
        testEntryEntityBuilder("hash-1"),
        testEntryEntityBuilder("hash-2"),
        testEntryEntityBuilder("hash-3"),
    )
) = UserEntity(
    id = id,
    name = name,
    favorites = favorites as MutableSet<EntryEntity>
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

fun testAddFavoriteRequest(
    hash: CID = "hash-1",
    name: String = "file-1",
    members: Set<Long> = setOf()
) = AddFavoriteRequest(
    hash = hash,
    name = name,
    members = members
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