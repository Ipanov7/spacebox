package com.spacebox.api.domain.entity

import javax.persistence.*

@Entity(name = "analysis_task")
class AnalysisTaskEntity(

    @Id
    val uuid: String,

    @OneToMany(cascade = [CascadeType.ALL])
    val entries: List<EntryEntity>,

    @OneToOne(cascade = [CascadeType.ALL])
    val output: EntryEntity,

    @Column(name = "public_key")
    val publicKey: String

)