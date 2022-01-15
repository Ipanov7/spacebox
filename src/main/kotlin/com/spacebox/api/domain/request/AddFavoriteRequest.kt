package com.spacebox.api.domain.request

import com.spacebox.api.domain.CID

data class AddFavoriteRequest(val hash: CID, val name: String, val members: Set<Long>)
