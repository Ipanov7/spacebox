package com.spacebox.api.controller

import com.spacebox.api.domain.common.Entry
import com.spacebox.api.manager.ContentManager
import org.apache.logging.log4j.kotlin.Logging
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/contents")
class ContentController(private val contentManager: ContentManager) {

    companion object : Logging

    @GetMapping("{hash}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getEntries(@PathVariable hash: String): ResponseEntity<List<Entry>> {
        logger.info("Listing children entries of object CID $hash")
        val entries = contentManager.getEntries(hash)
        return ResponseEntity.ok(entries)
    }


}