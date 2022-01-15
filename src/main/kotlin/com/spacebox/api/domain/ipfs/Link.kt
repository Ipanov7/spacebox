package com.spacebox.api.domain.ipfs

import com.spacebox.api.domain.common.CID

data class Link(
    val name: String,
    val hash: CID,
    val size: Long,
    val type: Int
)
