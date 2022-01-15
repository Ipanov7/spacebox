package com.spacebox.api.domain.entity

import javax.persistence.*

@Entity(name = "user_account")
class UserEntity(

    @Id
    val id: Long,

    @Column
    val name: String,

    @ManyToMany
    val favorites: Set<EntryEntity>
)