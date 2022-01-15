package com.spacebox.api.domain.common

data class AnalysisTask(val uuid: String, val entries: List<Entry>, val publicKey: String, val output: Entry)
