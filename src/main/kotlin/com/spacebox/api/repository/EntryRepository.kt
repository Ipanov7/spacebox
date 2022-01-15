package com.spacebox.api.repository

import com.spacebox.api.domain.entity.EntryEntity
import com.spacebox.api.domain.entity.UserEntity
import org.springframework.data.repository.CrudRepository

interface EntryRepository : CrudRepository<EntryEntity, String>