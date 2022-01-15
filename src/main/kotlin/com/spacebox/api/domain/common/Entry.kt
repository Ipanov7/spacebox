package com.spacebox.api.domain.common

import com.spacebox.api.domain.CID

data class Entry(
    val hash: CID,
    val size: Long,
    val type: Type
)
