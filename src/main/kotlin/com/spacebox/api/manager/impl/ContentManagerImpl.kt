package com.spacebox.api.manager.impl

import com.spacebox.api.client.IpfsClient
import com.spacebox.api.client.util.toEntry
import com.spacebox.api.domain.CID
import com.spacebox.api.domain.common.Entry
import com.spacebox.api.manager.ContentManager
import org.apache.logging.log4j.kotlin.Logging
import org.springframework.stereotype.Component
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.name
import kotlin.io.path.pathString
import kotlin.io.path.readBytes
import kotlin.io.path.writeText

@Component
class ContentManagerImpl(private val ipfsClient: IpfsClient) : ContentManager {
    companion object : Logging

    override fun getEntries(hash: CID): List<Entry> {
        logger.debug("Retrieving children of CID $hash")
        val result = ipfsClient.list(hash)
        val links = result.objects.first().links
        return links.map { toEntry(it) }
    }

    override fun getEntry(hash: CID): Entry {
        logger.debug("Retrieving entry {cid=$hash}")
        val stats =  ipfsClient.fileStats("/ipfs/$hash")
        return toEntry(stats)
    }

    override fun upload(content: String): Entry {
        logger.debug("Uploading content to IPFS...")
        return ipfsClient.add(content.toByteArray())
    }


}