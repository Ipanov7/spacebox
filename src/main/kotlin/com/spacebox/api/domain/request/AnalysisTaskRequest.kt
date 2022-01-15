package com.spacebox.api.domain.request

import com.spacebox.api.domain.common.CID

data class AnalysisTaskRequest(val uuid: String, val entries: List<CID>, val publicKey: String)
