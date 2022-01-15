package com.spacebox.api.domain.entity

import com.spacebox.api.domain.CID
import com.spacebox.api.domain.common.Type
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "entry")
class EntryEntity(

    @Id
    val hash: CID,

    @Column
    val size: Long,

    @Column
    val type: Type
)