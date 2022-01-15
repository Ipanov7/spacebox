package com.spacebox.api.manager.impl

import com.spacebox.api.client.IpfsClient
import com.spacebox.api.client.util.toEntry
import com.spacebox.api.domain.common.CID
import com.spacebox.api.domain.common.Entry
import com.spacebox.api.manager.ContentManager
import org.apache.logging.log4j.kotlin.Logging
import org.springframework.stereotype.Component
import java.nio.file.Files
import kotlin.io.path.name
import kotlin.io.path.writeBytes

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
        val stats = ipfsClient.fileStats("/ipfs/$hash")
        return toEntry(stats)
    }

    override fun upload(content: ByteArray): Entry {
        logger.debug("Uploading content to IPFS...")
        val path = Files.createTempFile("", "output")
        path.writeBytes(content)
        return ipfsClient.add(path.toFile(), "file", path.name)
    }


}