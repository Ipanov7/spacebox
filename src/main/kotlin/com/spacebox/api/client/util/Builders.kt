package com.spacebox.api.client.util

import com.spacebox.api.domain.common.AnalysisTask
import com.spacebox.api.domain.common.Entry
import com.spacebox.api.domain.common.Type
import com.spacebox.api.domain.entity.AnalysisTaskEntity
import com.spacebox.api.domain.entity.EntryEntity
import com.spacebox.api.domain.ipfs.FileStatsResult
import com.spacebox.api.domain.ipfs.Link

fun toEntry(link: Link) = Entry(
    hash = link.hash,
    size = link.size,
    type = toType(link.type)
)

fun toEntry(entity: EntryEntity) = Entry(
    hash = entity.hash,
    type = entity.type,
    size = entity.size
)

fun toEntry(fileStatsResult: FileStatsResult) = Entry(
    hash = fileStatsResult.hash,
    type = toType(fileStatsResult.type),
    size = fileStatsResult.size
)

fun toEntryEntity(entry: Entry) = EntryEntity(
    hash = entry.hash,
    type = entry.type,
    size = entry.size
)

private fun toType(typeAsInt: Int): Type {
    return when (typeAsInt) {
        1 -> Type.DIRECTORY
        2 -> Type.FILE
        else -> Type.UNKNOWN
    }
}

private fun toType(typeAsString: String): Type {
    return when (typeAsString) {
        "directory" -> Type.DIRECTORY
        "file" -> Type.FILE
        else -> Type.UNKNOWN
    }
}

fun toTaskEntity(analysisTask: AnalysisTask) = AnalysisTaskEntity(
    uuid = analysisTask.uuid,
    entries = analysisTask.entries.map { toEntryEntity(it) },
    output = toEntryEntity(analysisTask.output),
    publicKey = analysisTask.publicKey
)

fun toTask(analysisTaskEntity: AnalysisTaskEntity) = AnalysisTask(
    uuid = analysisTaskEntity.uuid,
    entries = analysisTaskEntity.entries.map { toEntry(it) },
    output = toEntry(analysisTaskEntity.output),
    publicKey = analysisTaskEntity.publicKey
)