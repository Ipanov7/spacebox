package com.spacebox.api.client

import com.spacebox.api.client.configuration.ClientConfiguration
import com.spacebox.api.domain.common.CID
import com.spacebox.api.domain.common.Entry
import com.spacebox.api.domain.ipfs.FileStatsResult
import com.spacebox.api.domain.ipfs.ListResult
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import java.io.File

@FeignClient(
    name = "ipfs-client", url = "\${ipfs.node.url}",
    configuration = [ClientConfiguration::class]
)
interface IpfsClient {
    @PostMapping(
        value = ["/api/v0/ls"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun list(@RequestParam arg: CID): ListResult

    @PostMapping(
        value = ["/api/v0/files/stat"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun fileStats(@RequestParam arg: CID): FileStatsResult

    @PostMapping(
        value = ["/api/v0/add"],
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun add(
        @RequestPart("file") file: File,
        @RequestHeader fieldName: String,
        @RequestHeader filename: String
    ): Entry


}