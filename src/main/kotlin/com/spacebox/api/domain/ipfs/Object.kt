package com.spacebox.api.domain.ipfs

import com.spacebox.api.domain.common.CID

data class Object(val hash: CID, val links: List<Link>)
