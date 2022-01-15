package com.spacebox.api.domain.common

data class Entry(
    val hash: CID,
    val size: Long,
    val type: Type
)
