package com.spacebox.api.domain.ipfs

import com.spacebox.api.domain.CID

data class FileStatsResult(
    val hash: CID,
    val size: Long,
    val type: String
)
