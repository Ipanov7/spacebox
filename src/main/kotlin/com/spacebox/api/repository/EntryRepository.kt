package com.spacebox.api.repository

import com.spacebox.api.domain.entity.EntryEntity
import org.springframework.data.repository.CrudRepository

interface EntryRepository : CrudRepository<EntryEntity, String>