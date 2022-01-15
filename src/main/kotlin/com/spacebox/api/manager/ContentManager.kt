package com.spacebox.api.manager

import com.spacebox.api.domain.CID
import com.spacebox.api.domain.common.Entry

interface ContentManager {
    fun getEntries(hash: CID): List<Entry>
    fun getEntry(hash: CID): Entry
    fun upload(content: String): Entry
}